package ru.unionfreearts.crawler;

import java.util.ArrayList;
import java.util.Scanner;

import com.sun.istack.internal.Nullable;

public class Main {
	private static Log log;

	public static void main(String[] args) {
		try {
			log = new Log();
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter q for quit");
			ArrayList<Parser> parsers = new ArrayList<Parser>();
			Parser p;
			int i = 1;
			while (true) {
				p = new Parser(i);
				if (p.start())
					parsers.add(p);
				else
					break;
				i++;
			}
			String s = sc.nextLine();
			while (!s.equals("q"))
				s = sc.nextLine();
			for (i = 0; i < parsers.size(); i++) {
				parsers.get(i).stop();
			}
			log.closeLog();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR (Main): " + e.getMessage());
		}
		System.out.println("FINISH Main Thread");
	}

	public static synchronized void appendLog(String msg, @Nullable Exception e) {
		try {
			log.appendLog(msg);
			if (e != null) {
				e.printStackTrace(log.getWriter());
				log.flush();
			}
		} catch (Exception ex) {
			System.out.println(msg);
			if (e != null)
				e.printStackTrace();
			ex.printStackTrace();
		}
	}
}
