package com.mysoft;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 08.12.15.
 */
public class JsParser {
    public static JsParser instance;

    private JsParser() {

    }

    public JsParser getInstance(){
        if(instance == null)
            instance = new JsParser();

        return instance;
    }


    public static String getTextByDocument(Document doc, String selector) {
        Elements elements = doc.select(selector);
        Element element = elements.get(0);

        return element.text();
    }

    public static String getTextByDocument(Document doc, String selector1, String selector2) {
        Elements elements = doc.select(selector1).select(selector2);
        Element element = elements.get(0);

        return element.text();
    }

    public static Document getDocument(String url) {
        Document document = null;

        try {
            document = Jsoup.connect(url).data("query", "Java")
                    .userAgent("Mozilla").timeout(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static List<String> getListWithTextByDocument(Document doc, String selector1, String selector2) {
        List<String> text = new ArrayList<>();
        Elements elements = doc.select(selector1).select(selector2);

        for (Element a : elements)
            text.add(a.text());

        return text;
    }

    public static List<String> getListWithTextByDocument(Document doc, String selector) {
        List<String> text = new ArrayList<>();
        Elements elements = doc.select(selector);

        for (Element a : elements)
            text.add(a.text());

        return text;
    }

    public static Elements getElements(Document document, String selector) {
        return document.select(selector);
    }

    public static Elements getElements(Document document, String selector1, String selector2) {
        return document.select(selector1).select(selector2);
    }

    public static List<String> getListTextByElements(Elements elements) {
        List<String> text = new ArrayList<>();

        for (Element element : elements)
            text.add(element.text());

        return text;
    }

    public static List<String> getListAttributeByElements(Elements elements, String attribute) {
        List<String> text = new ArrayList<>();

        for (Element element : elements)
            text.add(element.attr(attribute));

        return text;
    }

    public static String getDescription(Document doc, String selector) {
        return doc.select(selector).first().text();
    }

}

