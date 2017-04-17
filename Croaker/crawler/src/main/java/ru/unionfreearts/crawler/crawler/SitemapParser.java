package ru.unionfreearts.crawler.crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class SitemapParser {
	public static void main(String[] args) throws Exception {
		RobotsParser robotParser = new RobotsParser();
		ArrayList<String> urlFromRobotParser = new ArrayList<String>(robotParser.robotParser());
		// цикл потом включим, чтобы парсить все ссылки из robots.txt
		// for (int i = 0; i < urlFromRobotParser.size(); i++) {

		URL url = new URL(urlFromRobotParser.get(1)); // передаем нужный URL
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

		String inputLine;
		ArrayList<String> HtmlURL = new ArrayList<String>();

		while ((inputLine = in.readLine()) != null) { // парсим HTML
			if (inputLine.contains("https")) {
				System.out.println(inputLine);

				HtmlURL.add(inputLine.substring(5, (inputLine.length() - 6))); // пишем
																				// их
																				// в
																				// лист,
																				// убираем
																				// лишние
																				// символы
																				// с
																				// начала
																				// и
																				// конца
																				// строк.

			}

		}
		in.close();

		System.out.println(HtmlURL);// для тестирования работы, потом можно
									// удалить.

		// }
	}
}
