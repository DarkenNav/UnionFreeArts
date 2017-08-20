package ru.unionfreearts.crawler.entities;

public class Rank {
	private int page_id, person_id, count = 0;

	public Rank(int page_id, int person_id) {
		this.page_id = page_id;
		this.person_id = person_id;
	}

	public int getPageId() {
		return page_id;
	}

	public int getPersonId() {
		return person_id;
	}

	public int getCount() {
		return count;
	}

	public void addCount(int k) {
		count += k;
	}

	public boolean isNeed(int person_id) {
		return this.person_id == person_id;
	}
}
