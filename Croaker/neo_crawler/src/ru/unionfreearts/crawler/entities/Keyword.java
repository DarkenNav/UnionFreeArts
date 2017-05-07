package ru.unionfreearts.crawler.entities;

public class Keyword {
	private String word;
	private int person_id;
	
	public Keyword(int person_id, String word) {
		this.person_id = person_id;
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public int getPersonId() {
		return person_id;
	}
}
