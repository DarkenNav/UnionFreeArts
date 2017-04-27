package ru.unionfreearts.crawler;

public class Keyword {
	private String word;
	private int id; //person id
	
	public Keyword(int id, String word) {
		this.id = id;
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public int getId() {
		return id;
	}
}
