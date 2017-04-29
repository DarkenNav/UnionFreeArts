package ru.unionfreearts.crawler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

import ru.unionfreearts.crawler.entities.Keyword;
import ru.unionfreearts.crawler.entities.Page;
import ru.unionfreearts.crawler.entities.Rank;

public class DBHandler {
	final Pattern paDate = Pattern.compile(".*/20\\d\\d/\\d\\d/\\d\\d.*");
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
			e.printStackTrace();
			System.out.println("ERROR (DBHandler): " + e.getMessage());
		}
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

	public void addLink(String link) throws Exception {
		if (containsLink(link))
			return;
		PreparedStatement stmt = con.prepareStatement(
				"INSERT INTO pages (Url, SiteID, FoundDateTime, LastScanDate) VALUES (?, ?, ?, ?)");
		stmt.setString(1, link);
		stmt.setInt(2, site_id);
		if (paDate.matcher(link).matches()) {
			link = link.substring(link.indexOf("20"));
			link = link.substring(0, 10);
			stmt.setDate(3, new Date(java.util.Date.parse(link)));
		} else
			stmt.setDate(3, new Date(System.currentTimeMillis()));
		stmt.setDate(4, new Date(0));
		stmt.execute();
		stmt.close();
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
			e.printStackTrace();
			System.out.println("ERROR (saveRanks): " + e.getMessage());
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
			e.printStackTrace();
			System.out.println("ERROR (updateScanDate): " + e.getMessage());
		}
	}
}
