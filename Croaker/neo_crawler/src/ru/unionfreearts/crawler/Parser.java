package ru.unionfreearts.crawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Pattern;

import ru.unionfreearts.crawler.entities.Page;

public class Parser {
	private final String PAGE = "page", HREF = " href";
	private String site;
	private File temp_dir;
	private ArrayList<String> disallow = new ArrayList<String>();
	private int download_page;
	private boolean stop = false;
	private DBHandler base;

	public Parser(int site_id) throws Exception {
		base = new DBHandler(site_id);
		site = base.getSite();

	}

	public void start() throws Exception {
		new Thread(new Runnable() {
			public void run() {
				try {
					temp_dir = new File("temp");
					if (!temp_dir.exists())
						temp_dir.mkdir();
					System.out.println("start parsing site: " + site);
					parseRobotsFile();
					downloadPage(site, 0);
					System.out.println("parsing page #0");
					addLinksFromPage(0);
					startDownloadThread();
					countRankOnPage(0);
					getFile(PAGE + 0).delete();
					startParseThread();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ERROR (start): " + e.getMessage());
				}
			}
		}).start();
	}

	public void stop() {
		stop = true;
	}

	public boolean isRun() {
		return !stop;
	}

	private void startDownloadThread() {
		new Thread(new Runnable() {
			public void run() {
				try {
					base.loadPageList();
					Page page = base.getNextPage();
					while (!stop) {
						if (page == null) {
							base.loadPageList();
							page = base.getNextPage();
							if (page == null)
								break;
						} else {
							downloadPage(site + page.getLink(), page.getId());
							page = base.getNextPage();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ERROR (startDownloadThread): " + e.getMessage());
				}
				download_page = -1;
			}
		}).start();
	}

	private void startParseThread() {
		new Thread(new Runnable() {
			public void run() {
				try {
					int id;
					while (true) {
						for (File f : temp_dir.listFiles()) {
							if (!f.getName().contains(String.valueOf(download_page)) && f.getName().contains(PAGE)) {
								// first case - check that the page is not
								// loading at the moment
								// second case - error insurance
								id = Integer.parseInt(f.getName().substring(PAGE.length()));
								System.out.println("parsing page #" + id);
								addLinksFromPage(id);
								countRankOnPage(id);
								base.updateScanDate(id);
								f.delete();
								if (stop)
									break;
							}
						}
						if (download_page == -1)
							break;
						Thread.sleep(500);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ERROR (startParseThread): " + e.getMessage());
				}
				base.saveRanks();
				stop = true;
			}
		}).start();
	}

	private void countRankOnPage(int page_id) throws Exception {
		base.addRanksForNewPage(page_id);
		File f = getFile(PAGE + page_id);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "utf-8"));
		String line;
		StringBuilder page = new StringBuilder();
		while ((line = br.readLine()) != null) {
			page.append(line);
		}
		br.close();
		int n, k, i_open, i_close;
		for (int i = 0; i < base.countKeywords(); i++) {
			n = page.indexOf(base.getWord(i));
			k = 0;
			while (n > -1) {
				i_open = page.indexOf("<", n);
				i_close = page.indexOf(">", n);
				if (i_open < i_close)
					k++;
				n = page.indexOf(base.getWord(i), n + 1);
			}
			base.addRank(k, page_id, base.getPersonIdByKeyword(i));
		}
	}

	private void downloadPage(String link, int id) throws Exception {
		download_page = id;
		System.out.println("start download page #" + id + ": " + link);
		URL url = new URL(link);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		BufferedWriter bw = new BufferedWriter(new FileWriter(getFile(PAGE + id)));
		String line;
		boolean isBody = false;
		while ((line = br.readLine()) != null && !stop) {
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
		if (!stop)
			System.out.println("finish download page #" + id + ": " + link);
	}

	private void addLinksFromPage(int page_id) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(getFile(PAGE + page_id)));
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
		URL url = new URL(site + "/robots.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		String line;
		while ((line = br.readLine()) != null) {
			if (line.contains("Disallow")) {
				line = line.substring(line.indexOf(" ") + 1);
				if (!line.contains("*"))
					line += "*";
				line = line.replace(".", "/.").replace("*", ".*");
				if (!disallow.contains(line)) {
					disallow.add(line);
				}
			}
		}
		br.close();
	}

	private File getFile(String name) {
		File f = new File(temp_dir.toString() + "/" + name);
		return f;
	}
}
