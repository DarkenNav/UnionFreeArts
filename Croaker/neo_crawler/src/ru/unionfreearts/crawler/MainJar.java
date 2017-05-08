package ru.unionfreearts.crawler;

import java.awt.GraphicsEnvironment;
import java.io.Console;

public class MainJar {
	public static void main(String[] args) {
		try {
			Console console = System.console();
			if (console == null && !GraphicsEnvironment.isHeadless()) {
				String filename = Main.class.getProtectionDomain().getCodeSource().getLocation().toString()
						.substring(6);
				Runtime.getRuntime()
						.exec(new String[] { "cmd", "/c", "start", "cmd", "/k", "java -jar \"" + filename + "\"" });
			} else {
				Main.main(args);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR (Main): " + e.getMessage());
		}
		System.out.println("FINISH Main Thread");
	}
}
