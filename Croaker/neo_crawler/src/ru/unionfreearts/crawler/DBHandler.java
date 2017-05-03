package ru.unionfreearts.crawler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.unionfreearts.crawler.entities.Keyword;
import ru.unionfreearts.crawler.entities.Page;
import ru.unionfreearts.crawler.entities.Rank;

public class DBHandler {
	// for lenta.ru, gazeta.ru:
	private final Pattern paDate1 = Pattern.compile("20\\d\\d/\\d\\d/\\d\\d");
	// for rbc.ru:
	private final Pattern paDate2 = Pattern.compile("\\d\\d/\\d\\d/20\\d\\d");
	// for ria.ru:
	private final Pattern paDate3 = Pattern.compile("20\\d\\d\\d\\d\\d\\d");
	private final Date dateMin = new Date(java.util.Date.parse("2017/04/01"));
	private int site_id;
	private Date dateStart;
	private ArrayList<Keyword> keywords = new ArrayList<Keyword>();
	private ArrayList<Rank> ranks = new ArrayList<Rank>();
	private ArrayList<Page> pages = new ArrayList<Page>();
	private ArrayList<Integer> persons = new ArrayList<Integer>();
	private Connection con;

	public DBHandler(int site_id) {
		this.site_id = site_id;
		try {
			BufferedReader br = new BufferedReader(new FileReader("sql_config.ini"));
			br.readLine(); // tip
			String url = br.readLine();
			br.readLine(); // tip
			String user = br.readLine();
			br.readLine(); // tip
			String password = br.readLine();
			br.close();

			con = DriverManager.getConnection(url, user, password);

			loadPersonsList();
			loadKeywordsList();

			dateStart = new Date(System.currentTimeMillis());
		} catch (Exception e) {
			Main.appendLog(getSiteId() + "ERROR (DBHandler): ", e);
		}
	}

	private String getSiteId() {
		return "#" + site_id + " ";
	}

	public String getSite() throws Exception {
		PreparedStatement stmt = con.prepareStatement("SELECT name FROM sites WHERE id = ?");
		stmt.setInt(1, site_id);
		ResultSet rs = stmt.executeQuery();
		String site = null;
		if (rs.next())
			site = rs.getString(1);
		stmt.close();
		return site;
	}

	private void loadPersonsList() throws Exception {
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT id FROM persons");
		while (rs.next()) {
			persons.add(rs.getInt(1));
		}
		stmt.close();
	}

	private void loadKeywordsList() throws Exception {
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM keywords");
		while (rs.next()) {
			keywords.add(new Keyword(rs.getInt(1), rs.getString(2)));
		}
		stmt.close();
	}

	public int countPersons() {
		return persons.size();
	}

	public int getPersonId(int index) {
		return persons.get(index);
	}

	public boolean addLink(String link) {
		try {
			if (containsLink(link))
				return false;
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO pages (Url, SiteID, FoundDateTime, LastScanDate) VALUES (?, ?, ?, ?)");
			stmt.setString(1, link);
			stmt.setInt(2, site_id);
			Date d = parseDate(link);
			if (d.getTime() < dateMin.getTime())
				return false;
			stmt.setDate(3, d);
			stmt.setDate(4, new Date(0));
			stmt.execute();
			stmt.close();
			return true;
		} catch (Exception e) {
			Main.appendLog(getSiteId() + "ERROR (addLink): ", e);
		}
		return false;
	}

	private Date parseDate(String s) {
		try {
			Matcher m = paDate1.matcher(s);
			if (m.find()) { // 2017/05/01
				s = s.substring(m.start());
				s = s.substring(0, 10);
				return new Date(java.util.Date.parse(s));
			}
			m = paDate2.matcher(s);
			if (m.find()) { // 01/05/2017
				s = s.substring(m.start());
				s = s.substring(0, 10);
				return new Date(java.util.Date.parse(s));
			}
			m = paDate3.matcher(s);
			if (m.find()) { // 20170501
				s = s.substring(m.start());
				s = s.substring(0, 8);
				s = s.substring(0, 4) + "/" + s.substring(4, 6) + "/" + s.substring(6);
				return new Date(java.util.Date.parse(s));
			}
		} catch (Exception e) {
		}
		return new Date(System.currentTimeMillis());
	}

	private boolean containsLink(String link) throws Exception {
		PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM pages WHERE Url = ? AND SiteID = ?");
		stmt.setString(1, link);
		stmt.setInt(2, site_id);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int k = rs.getInt(1);
		stmt.close();
		return k > 0;
	}

	private void loadPageList() throws Exception {
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM pages WHERE SiteID = ? AND LastScanDate < ?");
		stmt.setInt(1, site_id);
		stmt.setDate(2, dateStart);
		ResultSet rs = stmt.executeQuery();
		int k = 0;
		while (rs.next() && k < 30) { // 30 - limit
			pages.add(new Page(rs.getString(2), rs.getInt(1)));
			k++;
		}
		stmt.close();
	}

	public Page getNextPage() throws Exception {
		Page page = null;
		if (pages.size() == 0)
			loadPageList();
		if (pages.size() > 0) {
			page = pages.get(0);
			pages.remove(0);
		}
		return page;
	}

	public int countKeywords() {
		return keywords.size();
	}

	public String getWord(int index_word) {
		return keywords.get(index_word).getWord();
	}

	public int getPersonIdByKeyword(int index_word) {
		return keywords.get(index_word).getPersonId();
	}

	public void addRank(int count, int person_id) {
		for (int i = 0; i < ranks.size(); i++) {
			if (ranks.get(i).isNeed(person_id)) {
				ranks.get(i).addCount(count);
				break;
			}
		}
	}

	public void addRanksForNewPage(int page_id) {
		for (int i = 0; i < countPersons(); i++) {
			ranks.add(new Rank(page_id, getPersonId(i)));
		}
	}

	public void saveRanks() {
		try {
			PreparedStatement stmt;
			for (int i = 0; i < ranks.size(); i++) {
				stmt = con.prepareStatement("INSERT INTO PersonPageRank (PersonID, PageID, Rank) VALUES (?, ?, ?)");
				stmt.setInt(1, ranks.get(i).getPersonId());
				stmt.setInt(2, ranks.get(i).getPageId());
				stmt.setInt(3, ranks.get(i).getCount());
				stmt.execute();
				stmt.close();
			}
		} catch (Exception e) {
			Main.appendLog(getSiteId() + "ERROR (saveRanks): ", e);
		}
		ranks.clear();
	}

	public void updateScanTime(int page_id) {
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE pages SET LastScanDate = ? WHERE id = ?");
			stmt.setDate(1, new Date(System.currentTimeMillis()));
			stmt.setInt(2, page_id);
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			Main.appendLog(getSiteId() + "ERROR (updateScanDate) #" + page_id + ":", e);
		}
	}

	public void deleteLink(int page_id) {
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM pages WHERE id = ?");
			stmt.setInt(1, page_id);
			stmt.execute();
			stmt.close();
			Main.appendLog("DELETED page #" + page_id, null);
		} catch (Exception e) {
			Main.appendLog(getSiteId() + "ERROR (deleteLink) #" + page_id + ":", e);
		}
	}

	public void replaceLink(int page_id, String new_link) {
		try {
			if (containsLink(new_link)) {
				deleteLink(page_id);
				return;
			}
			PreparedStatement stmt = con.prepareStatement("UPDATE pages SET Url = ? WHERE id = ?");
			stmt.setString(1, new_link);
			stmt.setInt(2, page_id);
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			Main.appendLog(getSiteId() + "ERROR (replaceLink) #" + page_id + ":", e);
		}
	}
}
