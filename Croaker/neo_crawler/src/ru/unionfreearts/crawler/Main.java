package ru.unionfreearts.crawler;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter id site for parsing:");
			int id = sc.nextInt();
			System.out.println("Enter q for quit");	
			Parser parser = new Parser(id);
			parser.start();			
			String s = sc.nextLine();
			while(!s.equals("q") && parser.isRun())
				s = sc.nextLine();	
			parser.stop();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR (Main): " + e.getMessage());
		}
	}
}
