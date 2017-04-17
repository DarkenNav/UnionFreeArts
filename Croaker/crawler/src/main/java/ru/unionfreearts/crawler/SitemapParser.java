package ru.unionfreearts.crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class SitemapParser {
	public ArrayList<String> siteMapParser() throws Exception {
		RobotsParser robotParser = new RobotsParser();
		ArrayList<String> urlFromRobotParser = new ArrayList<String>(robotParser.robotParser());
		ArrayList<String> htmlURL = new ArrayList<String>();
		// цикл потом включим, чтобы парсить все ссылки из robots.txt
		//for (int i = 0; i < urlFromRobotParser.size(); i++) {

			URL url = new URL(urlFromRobotParser.get(1)); // передаем нужный URL
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null) { // парсим HTML
				if (inputLine.contains("https")) {
					System.out.println(inputLine);
					// пишем их в лист, убираем ишние символы с начала и конца
					// строк.
					htmlURL.add(inputLine.substring(5, inputLine.length() - 6));
				}

			}
			in.close();

			System.out.println(htmlURL);// для тестирования работы, потом можно
										// удалить.
			

		//}
		return htmlURL;

	}
}
