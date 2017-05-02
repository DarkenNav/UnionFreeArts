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
	private final String HREF = " href";
	private String site, download_page;
	private File temp_dir;
	private ArrayList<String> disallow = new ArrayList<String>();
	private boolean stop = false;
	private DBHandler base;
	private int site_id;

	public Parser(int site_id) throws Exception {
		this.site_id = site_id;
		base = new DBHandler(site_id);
		site = base.getSite();
	}

	public boolean start() {
		if (site == null)
			return false;
		new Thread(new Runnable() {
			public void run() {
				try {
					temp_dir = new File("temp" + site_id);
					if (temp_dir.exists()) {
						for (File f : temp_dir.listFiles()) {
							f.delete();
						}
					} else
						temp_dir.mkdir();
					System.out.println(getSiteId() + "start parsing site: " + site);
					parseRobotsFile();
					downloadMainPage();

					startParseThread();
					startDownloadThread();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(getSiteId() + "ERROR (Parser): " + e.getMessage());
				}
			}
		}).start();
		return true;
	}

	public void downloadMainPage() throws Exception {
		if (base.addLink("/")) { // if it first run on this site
			Page page = base.getNextPage();
			downloadPage(site + page.getLink(), page.getId());
			System.out.println(getSiteId() + "parsing main page (#" + page.getId() + ")");
			addLinksFromPage(page.getId());
			countRankOnPage(page.getId());
		} else {
			download_page = HREF; // flag "it's start"
		}
	}

	private String getSiteId() {
		return "#" + site_id + " ";
	}

	public void stop() {
		stop = true;
	}

	private void startDownloadThread() {
		new Thread(new Runnable() {
			public void run() {
				try {
					Page page;
					while (!stop && (page = base.getNextPage()) != null) {
						downloadPage(site + page.getLink(), page.getId());
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(getSiteId() + "ERROR (startDownloadThread): " + e.getMessage());
				}
				download_page = null;
				System.out.println(getSiteId() + "FINISH Download Thread");
			}
		}).start();
	}

	private void startParseThread() {
		new Thread(new Runnable() {
			public void run() {
				try {
					int id;
					while (download_page != null) {
						for (File f : temp_dir.listFiles()) {
							// check that the page is not loading at the moment:
							if (!f.getName().equals(download_page)) {
								id = Integer.parseInt(f.getName());
								System.out.println(getSiteId() + "parsing page #" + id);
								addLinksFromPage(id);
								countRankOnPage(id);
								if (stop)
									break;
							}
						}
						Thread.sleep(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(getSiteId() + "ERROR (startParseThread): " + e.getMessage());
				}
				stop = true;
				System.out.println(getSiteId() + "FINISH Parse Thread");
			}
		}).start();
	}

	private void countRankOnPage(int page_id) throws Exception {
		base.addRanksForNewPage(page_id);
		File f = getFile(page_id);
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
			base.addRank(k, base.getPersonIdByKeyword(i));
		}
		base.saveRanks();
		base.updateScanTime(page_id);
		f.delete();
	}

	private void downloadPage(String link, int id) throws Exception {
		File f = getFile(id);
		if (f.exists())
			return;
		download_page = f.getName();
		System.out.println(getSiteId() + "start download page #" + id + ": " + link);
		URL url = new URL(link);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
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
			System.out.println(getSiteId() + "finish download page #" + id + ": " + link);
	}

	private void addLinksFromPage(int page_id) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(getFile(page_id)));
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
					break;
				link = link.substring(0, link.indexOf(quote));
				i = line.indexOf(HREF, i);
				if (link.indexOf(site) > 0) // share link: ...um=social&href=https://site....
					continue;
				if (link.indexOf("//") == 0)
					link = "http:" + link;
				if (!isDisallow(link)) {
					if (link.contains(site)) // remove http://site.ru
						link = link.substring(site.length());
					if (link.length() > 1) {
						if (link.indexOf("/") > 0) // bad link
							continue;
						base.addLink(link);
					}
				}
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

	private File getFile(int name) {
		File f = new File(temp_dir.toString() + "/" + name);
		return f;
	}
}
