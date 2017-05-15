package ru.unionfreearts.crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class HtmlParser {
	// На вход получаем arrayList HTML URL и слово от пользователя, ищем слова
	// на сайтах и считаем рейтинг.
	public int htmlParser(String searchWord) throws Exception {
		int rating = 0;
		SitemapParser sitemapParser = new SitemapParser();
		ArrayList<String> urlFromSitemapParser = new ArrayList<String>(sitemapParser.siteMapParser());
		// цикл потом включим, чтобы парсить все ссылки из sitemap.xml
		// for (int i = 0; i < urlFromRobotParser.size(); i++) {

		URL url = new URL("https://ru.wikipedia.org/wiki/Sitemaps"); // передаем нужный URL
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

		String inputLine;

		while ((inputLine = in.readLine()) != null) { // парсим HTML
			// слово получаем от пользователя.
			if (inputLine.contains(searchWord)) {
				//System.out.println(inputLine);
				// добавляем +1 к рейтингу.
				rating++;
			}

		}
		in.close();

		System.out.println(rating);// для тестирования работы, потом можно
									// удалить.

		// }

		return rating;

	}
}
