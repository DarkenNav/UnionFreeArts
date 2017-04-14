package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader {

    public String download(String url){ // метод скачивающий страницы заданному адресу
        StringBuilder page = new StringBuilder();; // текст страницы
        BufferedReader reader = null;
        String line;
        try {
            URL site = new URL(url);    // адрес страницы
            reader = new BufferedReader(new InputStreamReader(site.openStream()));
            while ((line = reader.readLine()) != null) { // построчное чтение
                page.append(line).append("\n");
            }
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return page.toString();
    }
}
