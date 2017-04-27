package ru.unionfreearts.crawler;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ru.unionfreearts.crawler.entities.Keyword;
import ru.unionfreearts.crawler.entities.Page;
import ru.unionfreearts.crawler.entities.Rank;

public class DBHandler {
	private static final String url = "jdbc:mysql://localhost:3306/ufartdb";
	private static final String user = "root";
	private int site_id;
	private ArrayList<Keyword> keywords = new ArrayList<Keyword>();
	private ArrayList<Rank> ranks = new ArrayList<Rank>();
	private ArrayList<Integer> persons = new ArrayList<Integer>();
	private ArrayList<Page> pages = new ArrayList<Page>();
	private Connection con;

	public DBHandler(String password, int site_id) {
		this.site_id = site_id;
		try {
//			con = DriverManager.getConnection(url, user, password);

			loadPersonsList();
			loadKeywordsList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSite() {
		return "https://lenta.ru";
//		try {
//			PreparedStatement stmt = con.prepareStatement("SELECT name FROM sites WHEN id = ?;");
//			stmt.setInt(1, site_id);
//			ResultSet rs = stmt.executeQuery();
//			return rs.getString(0);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	private void loadPersonsList() {
		persons.add(1);
		persons.add(2);
	}

	private void loadKeywordsList() {
		keywords.add(new Keyword(1, "Путин"));
		keywords.add(new Keyword(2, "Медведев"));
	}

	public int countPersons() {
		return persons.size();
	}

	public int getPersonId(int index) {
		return persons.get(index);
	}

	public void addLink(String link) throws Exception {
		if (!containsLink(link))
			pages.add(new Page(link, pages.size() + 1, site_id));
	}

	private boolean containsLink(String link) throws Exception {
		for (int i = 0; i < pages.size(); i++) {
			if (pages.get(i).getLink().equals(link)) {
				return true;
			}
		}
		return false;
	}

	public String getLink(int index_page) {
		return pages.get(index_page).getLink();
	}

	public int countPages() {
		return pages.size();
	}

	public int getPageId(int index_page) {
		return pages.get(index_page).getId();
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

	public void addRank(int count, int page_id, int person_id) {
		for (int i = 0; i < ranks.size(); i++) {
			if (ranks.get(i).isNeed(page_id, person_id)) {
				ranks.get(i).addCount(count);
				break;
			}
		}
	}

	public int addRanksForNewPage(int index_page) {
		int start_index = ranks.size();
		for (int i = 0; i < countPersons(); i++) {
			ranks.add(new Rank(getPageId(index_page), getPersonId(i)));
		}
		return start_index;
	}

	public void saveRanks() {
		for (int i = 0; i < ranks.size(); i++) {
			System.out.println("person id" + ranks.get(i).getPersonId() + " on page id" + ranks.get(i).getPageId()
					+ ": " + ranks.get(i).getCount());
		}
	}
}
