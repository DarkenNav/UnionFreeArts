package ru.unionfreearts.crawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Parser {
	private final String DISALLOW = "Disallow", PAGE = "page", HREF = " href";
	private String site;
	private ArrayList<String> disallow;
	private ArrayList<Keyword> keywords;
	private boolean complete_download = false, complete_parse = true;
	private HashMap<Integer, Integer> persons; // person id, count
	private int site_id, count_page = 0, current_page = 0, index_download = 0;

	public Parser(int site_id) {
		this.site_id = site_id;
	}

	public void start() throws Exception {
		site = getSite(site_id);
		System.out.println("start parse site: " + site);
		parseRobotsFile();
		loadDisallowList();
		loadPersonsList();
		loadKeywordsList();
		downloadPage(site);
		addLinksFromPage(current_page);
		countRankOnPage(current_page);
		downloadPagesFromList();
	}

	private String getSite(int id) {
		return "https://lenta.ru";
	}

	private void downloadPagesFromList() {
		new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("downloadPagesFromList: " + index_download);
					ArrayList<String> pages = getPagesList(index_download);
					if (pages.size() == 0) {
						complete_download = true;
						checkFinish();
						return;
					}
					index_download += pages.size();
					for (int i = 0; i < pages.size(); i++) {
						downloadPage(site + pages.get(i));
						current_page++;
						if (complete_parse)
							parsePage(current_page);						
						if (count_page == 10) { // tut temp limit
							complete_download = true;
							checkFinish();
							return;
						}
					}
					downloadPagesFromList();
				} catch (Exception e) {
					System.out.println("ERROR (downloadPagesFromList): " + e.getMessage());
				}
			}
		}).start();
	}

	private void parsePage(final int start_index) {
		new Thread(new Runnable() {
			public void run() {
				try {
					int i = start_index;
					File f = getFile(PAGE + i);
					while (f.exists()) {
						System.out.println("parsePage: " + i);
						addLinksFromPage(i);
						countRankOnPage(i);
						f.delete();
						i++;
						f = getFile(PAGE + i);
					}
				} catch (Exception e) {
					System.out.println("ERROR (parsePage): " + e.getMessage());
				}
				complete_parse = true;
				checkFinish();
			}
		}).start();
	}

	private void checkFinish() {
		if (complete_download && complete_parse) {
			for (int i : persons.keySet()) {
				System.out.println("person #" + i + ": " + persons.get(i));
			}
		}
	}

	private ArrayList<String> getPagesList(int index) throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		File f = getFile("pages.txt");
		if (!f.exists())
			return list;
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line;		
		int i = 0;
		while ((line = br.readLine()) != null) {
			if (i == index)
				list.add(line);
			else
				i++;
		}
		br.close();
		return list;
	}

	private void loadPersonsList() {
		persons = new HashMap<Integer, Integer>(); // person id, count
		persons.put(1, 0); // "Путин"
		persons.put(2, 0); // "Медведев"
	}

	private void loadKeywordsList() {
		keywords = new ArrayList<Keyword>();
		keywords.add(new Keyword(1, "Путин"));
		keywords.add(new Keyword(2, "Медведев"));
	}

	private void countRankOnPage(int index_page) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(getFile(PAGE + index_page)));
		String line;
		int k, n;
		while ((line = br.readLine()) != null) {
			for (int i = 0; i < keywords.size(); i++) {
				k = persons.get(keywords.get(i).getId());
				n = line.indexOf(keywords.get(i).getWord());
				while (n > -1) { // while - if several keyword on the line
					k++;
					n = line.indexOf(keywords.get(i).getWord(), n + 1);
				}
				persons.put(keywords.get(i).getId(), k);
			}
		}
		br.close();
	}

	private void downloadPage(String link) throws Exception {
		System.out.println("start downloadPage (" + count_page + "): " + link);
		URL url = new URL(link);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		BufferedWriter bw = new BufferedWriter(new FileWriter(getFile(PAGE + count_page)));
		count_page++;
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
		System.out.println("finish downloadPage (" + (count_page-1) + "): " + link);
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
					if (link.length() > 1 && !containsLink(link)) {
						addLink(link);
					}
				}
				i = line.indexOf(HREF, i);
			}
		}
		br.close();
	}

	private void addLink(String link) throws Exception {
		System.out.println("add: " + link);
		BufferedWriter bw = new BufferedWriter(new FileWriter(getFile("pages.txt"), true));
		bw.write(link);
		bw.newLine();
		bw.close();
	}

	private boolean containsLink(String link) throws Exception {
		File f = getFile("pages.txt");
		if (!f.exists())
			return false;
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line;
		while ((line = br.readLine()) != null) {
			if (line.equals(link)) {
				br.close();
				return true;
			}
		}
		br.close();
		return false;
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
		disallow = new ArrayList<String>();
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
