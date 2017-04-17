package ru.unionfreearts.crawler.crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class SitemapParser {
	public static void main(String[] args) throws Exception {

		URL url = new URL("https://yandex.ru/blog/sitemap.xml"); //передаем нужный URL
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

		String inputLine;
		ArrayList<String> HtmlURL = new ArrayList<String>();
		
			while ((inputLine = in.readLine()) != null) { //парсим HTML
				if (inputLine.contains("https")) { 
					System.out.println(inputLine);
					
					HtmlURL.add(inputLine.substring(5, (inputLine.length()-6))); //пишем их в лист


				}

			}
			in.close();
		System.out.println(HtmlURL);

	}
	
}
