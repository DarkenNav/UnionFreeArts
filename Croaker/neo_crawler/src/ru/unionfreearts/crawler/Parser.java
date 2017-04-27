package ru.unionfreearts.crawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Pattern;

import ru.unionfreearts.crawler.entities.Keyword;
import ru.unionfreearts.crawler.entities.Page;
import ru.unionfreearts.crawler.entities.Rank;

public class Parser {
	private final String DISALLOW = "Disallow", PAGE = "page", HREF = " href";
	private String site;
	private ArrayList<String> disallow = new ArrayList<String>();
	private boolean complete_download = false;
	private int count_page = 0;
	private DBHandler base;

	public Parser(int site_id) {
		base = new DBHandler("admin", site_id);
		site = base.getSite();
	}

	public void start() throws Exception {
		System.out.println("start parsing site: " + site);
		parseRobotsFile();
		loadDisallowList();
		downloadPage(site);
		System.out.println("parsing page #0");
		addLinksFromPage(0);
		startDownloadThread();
		countRankOnPage(0);
		getFile(PAGE + 0).delete();
		startParseThread();
	}

	private void startDownloadThread() {
		new Thread(new Runnable() {
			public void run() {
				try {
					int i = 0;
					while (i < base.countPages()) {
						downloadPage(site + base.getLink(i));
						if (count_page == 10) { // tut temp limit
							complete_download = true;
							return;
						}
						i++;
					}
				} catch (Exception e) {
					complete_download = true;
					System.out.println("ERROR (startDownloadThread): " + e.getMessage());
				}
				complete_download = true;
			}
		}).start();
	}

	private void startParseThread() {
		new Thread(new Runnable() {
			public void run() {
				try {
					int i = 1;
					File f = getFile(PAGE + i);
					while (true) {
						if (f.exists() && i < count_page) {
							System.out.println("parsing page #" + i);
							addLinksFromPage(i);
							countRankOnPage(i);
							f.delete();
							i++;
							f = getFile(PAGE + i);
						} else {
							if (complete_download)
								break;
							System.out.println("sleep parse");
							Thread.sleep(500);
						}
					}
				} catch (Exception e) {
					System.out.println("ERROR (startParseThread): " + e.getMessage());
				}
				base.saveRanks();
			}
		}).start();
	}	

	private void countRankOnPage(int index_page) throws Exception {
		base.addRanksForNewPage(index_page);
		BufferedReader br = new BufferedReader(new FileReader(getFile(PAGE + index_page)));
		String line;
		StringBuilder page = new StringBuilder();
		while ((line = br.readLine()) != null) {
			page.append(line);
		}
		br.close();
		int n, k;
		for (int i = 0; i < base.countKeywords(); i++) {
			n = page.indexOf(base.getWord(i));
			k = 0;
			while (n > -1) {
				k++;
				n = page.indexOf(base.getWord(i), n + 1);
			}
			base.addRank(k, base.getPageId(index_page), base.getPersonIdByKeyword(i));
		}
	}

	private void downloadPage(String link) throws Exception {
		System.out.println("start download page #" + count_page + ": " + link);
		URL url = new URL(link);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		BufferedWriter bw = new BufferedWriter(new FileWriter(getFile(PAGE + count_page)));
		String line;
		boolean isBody = false;
		while ((line = br.readLine()) != null) {
			if (!isBody) {
				if (line.contains("<body")) {
					isBody = true;
					bw.write(line.substring(line.indexOf("<body")));
					bw.newLine();
					bw.flush();
				}
			} else {
				bw.write(line);
				bw.newLine();
				bw.flush();
			}
		}
		br.close();
		bw.close();
		System.out.println("finish download page #" + count_page + ": " + link);
		count_page++;
	}

	private void addLinksFromPage(int index_page) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(getFile(PAGE + index_page)));
		String line, link;
		char quote;
		int i;
		while ((line = br.readLine()) != null) {
			i = line.indexOf(HREF, 0);
			while (i > -1) { // while - if several links on the line
				i += HREF.length() + 2; // 2 - it is this: ="
				quote = line.substring(i - 1, i).toCharArray()[0]; // " or '
				link = line.substring(i);
				if (link.indexOf(quote) == -1)
					System.out.println(i + ", line: " + line);
				link = link.substring(0, link.indexOf(quote));
				if (link.indexOf("//") == 0)
					link = "http:" + link;
				if (!isDisallow(link)) {
					if (link.contains(site)) // remove http://site.ru
						link = link.substring(site.length());
					if (link.length() > 1) {
						base.addLink(link);
					}
				}
				i = line.indexOf(HREF, i);
			}
		}
		br.close();
	}

	private boolean isDisallow(String url) {
		if (!url.contains("/")) // in-page or incorrect links
			return true;
		if (url.contains("http") && !url.contains(site)) // links to other sites
			return true;
		if (disallow == null)
			return false;
		Pattern pattern;
		for (int i = 0; i < disallow.size(); i++) {
			pattern = Pattern.compile(disallow.get(i));
			if (pattern.matcher(url).matches())
				return true;
		}
		return false;
	}

	private void parseRobotsFile() throws Exception {
		File f = getFile(DISALLOW);
		if (f.exists())
			return;
		ArrayList<String> list = new ArrayList<String>();
		URL url = new URL(site + "/robots.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		String line;
		while ((line = br.readLine()) != null) {
			if (line.contains(DISALLOW)) {
				line = line.substring(line.indexOf(" ") + 1);
				if (!line.contains("*"))
					line += "*";
				if (!list.contains(line)) {
					list.add(line);
					bw.write(line);
					bw.newLine();
					bw.flush();
				}
			}
		}
		br.close();
		bw.close();
	}

	private void loadDisallowList() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(getFile(DISALLOW)));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.replace(".", "/.").replace("*", ".*");
			disallow.add(line);
		}
		br.close();
	}

	private File getFile(String name) {
		File f = new File(site.substring(site.indexOf("/") + 2));
		if (!f.exists())
			f.mkdir();
		f = new File(f.toString() + "/" + name);
		return f;
	}

}
