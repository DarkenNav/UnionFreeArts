package ru.unionfreearts.crawler.entities;

public class Page {
	private String link;
	private int id, site_id;
	
	public Page(String link, int id, int site_id) {
		this.link = link;
		this.id = id;
		this.site_id = site_id;
	}

	public String getLink() {
		return link;
	}

	public int getId() {
		return id;
	}

	public int getSiteId() {
		return site_id;
	}
}
