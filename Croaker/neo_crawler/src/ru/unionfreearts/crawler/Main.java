package ru.unionfreearts.crawler;

public class Main {
	public static void main(String[] args) {
		try {
			Parser parser = new Parser(1);
			parser.start();
		} catch (Exception e) {
			System.out.println("ERROR (Main): " + e.getMessage());
		}
	}
}
