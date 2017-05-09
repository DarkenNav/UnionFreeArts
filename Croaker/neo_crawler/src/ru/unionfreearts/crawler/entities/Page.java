package ru.unionfreearts.crawler.entities;

public class Page {
	private String link;
	private int id;
	
	public Page(String link, int id) {
		this.link = link;
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public int getId() {
		return id;
	}
}
