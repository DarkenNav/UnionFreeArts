package ru.unionfreearts.crawler.crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class RobotsParser {

	public ArrayList<String> robotParser() throws Exception {// на вход
																// принимаем
																// String URL,
																// допишем
																// потом, пока
																// для теста.

		URL url = new URL("http://yandex.ru/robots.txt");// заходим на сайт и
															// читаем файл
															// robots.txt
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

		String inputLine;
		ArrayList<String> sitemapURL = new ArrayList<String>();

		while ((inputLine = in.readLine()) != null) {
			if (inputLine.contains("sitemap.xml")) { // ищем сслыку на
														// sitemap.xml
				//System.out.println(inputLine);// тут пока печатаем, в
												// будущем
												// делаем, что хотим.

				sitemapURL.add(inputLine.substring(9));// закидываем URL
														// sitemap.xml в лист.

			}

		}
		in.close();
		//System.out.println(sitemapURL);//печатать в финале не обязательно, здесь для проверки работоспособности
		return sitemapURL;

	}

}
