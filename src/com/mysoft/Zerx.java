package com.mysoft;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by root on 14.12.15.
 */
public class Zerx extends Urls {
    private static Zerx instance;

    private Zerx() {
    }

    public static Zerx getInstance() {
        if (instance == null)
            instance = new Zerx();
        return instance;
    }

    public String getPlayerCode(String titleFilm, String producer) {
        Document document = JsParser.getDocument(urlZerxSearch + titleFilm);
        String strProducer;
        Elements elements = document.select("div.header a.ftitle");

        if (elements.size() == 0)
            return null;

        for (Element element : elements) {
            String title = element.text();
            document = JsParser.getDocument(element.attributes().get("href"));
            try {
                strProducer = document.select("[href^=/actors/").first().text();
            } catch (NullPointerException e) {
                break;
            }

            if (strProducer.toLowerCase().equals(producer.toLowerCase()) && titleFilm.regionMatches(true, 0, title, 0, title.length() - 2))
                return document.getElementById("MT_overroll").html();
        }

        return null;
    }
}
