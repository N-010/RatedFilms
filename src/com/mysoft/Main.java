package com.mysoft;

import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 24.10.15.
 */

class Main {
    public static String test() {

        return "This is a test";
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        JsParser jsParser = new JsParser();
        Database db = new Database();
        Document doc = JsParser.getDocument(Urls.urlKinopoiskParse);
        List<String> titles = jsParser.getListWhithTextByDocument(doc, "td", "a.all[href^=/film/");
        List<String> hrefs = JsParser.getListAttributeByElements(JsParser.getElements(doc, "td", "a.all[href^=/film/"), "href");
        List<String> descriptions = new ArrayList<>();
        List<Document> documents = new ArrayList<>();
        List<String> listUrlImg = new ArrayList<>();
        Connection connection = db.getDatabaseConnect("jdbc:mysql://localhost:3306/RatedFilms", "root", "root");
        String column = "title";
        String value = new String();
        String addValue;
        Document doc2;

        for (int i = 0; i < 250; i++) {
            System.out.println(hrefs.get(i));
        }

        for (int i = 0; i < 250; i++) {
            System.out.println(i);
            documents.add(JsParser.getDocument(Urls.urlKinopoisk + hrefs.get(i)));
        }


        for (int i = 0; i < 250; i++) {
            descriptions.add(jsParser.getDescription(documents.get(i), "div.brand_words[itemprop=description]"));
            db.UpdateValue(connection, "Rated", "description = \"" + descriptions.get(i) + "\"", "id = " + (i + 1));
            System.out.println(descriptions.get(i));

        }
        for (int i = 0; i < 250; i++) {
            Database.AddValue(connection, "title", titles.get(i));
        }
        for (Integer i = 0; i < 250; i++)
            System.out.println(hrefs.get(i));

    }

}
