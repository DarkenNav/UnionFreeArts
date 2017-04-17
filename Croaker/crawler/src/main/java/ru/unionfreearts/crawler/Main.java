package ru.unionfreearts.crawler;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) throws Exception
    {
//        RobotsParser robotParser = new RobotsParser();
//        robotParser.robotParser();
//    	SitemapParser sitemapParser = new SitemapParser();
//    	sitemapParser.siteMapParser();
    	HtmlParser htmlParser = new HtmlParser();
    	htmlParser.htmlParser("Sitemap");
    	System.out.println("END");
    	
    }
}
