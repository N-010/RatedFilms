package com.mysoft;

import org.jsoup.Jsoup;

import javax.swing.text.Document;
import java.io.IOException;

/**
 * Created by root on 08.12.15.
 */
public class JsoupParser {
    static Document getDocument(String url) {
        Document document = null;
        try {
            document = (Document) Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }


}

