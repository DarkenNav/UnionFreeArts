package ru.unionfreearts.crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class RobotsParser {

	// На вход принимаем String URL, допишем потом, пока для теста.
	public ArrayList<String> robotParser() throws Exception {
		// Заходим на сайт и читаем файл robots.txt

		URL url = new URL("https://yandex.ru/robots.txt");
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

		String inputLine;
		ArrayList<String> sitemapURL = new ArrayList<String>();

		while ((inputLine = in.readLine()) != null) {
			// Ищем сслыки на sitemap.xml
			if (inputLine.contains("sitemap.xml")) {
				System.out.println(inputLine);// тут пока печатаем, в
												// будущем
												// делаем, что хотим.

				sitemapURL.add(inputLine.substring(9));// закидываем URL
														// sitemap.xml в лист.

			}

		}
		in.close();
		System.out.println(sitemapURL);// печатать в финале не обязательно,
										// здесь для проверки работоспособности
		return sitemapURL;

	}

}
