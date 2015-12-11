package com.mysoft;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by root on 24.10.15.
 */

public class Main {

    public static List<FilmInformation> getListFileInformation() throws SQLException, ClassNotFoundException {
        List<FilmInformation> filmInformationList = new ArrayList<>();
        Database database = new Database();
        Connection connection = database.getDatabaseConnect("jdbc:mysql://localhost:3306/RatedFilms", "root", "root");
        ResultSet resultSet = database.getResultSet(connection, "SELECT title, description, imgURL FROM Rated");


        return filmInformationList;
    }

    public static String getNumberToString(String string) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(string);
        String number = null;
        int start = 0;

        while (matcher.find(start)) {
            number = string.substring(matcher.start(), matcher.end());
            start = matcher.end();
        }
        return number;

    }

    public static void InitialDatabase() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        Document mainPage = JsParser.getDocument(Urls.urlKinopoiskParse);
        List<String> titleList = JsParser.getListWhithTextByDocument(mainPage, "td", "a.all[href^=/film/");
        List<String> hrefList = JsParser.getListAttributeByElements(JsParser.getElements(mainPage, "td", "a.all[href^=/film/"), "href");
        List<Document> pageList = new ArrayList<>();
        List<String> imageUrlList = new ArrayList<>();
        List<String> descriptionList = new ArrayList<>();
        String description;
        String imageUrl;
        Connection connection = database.getDatabaseConnect("jdbc:mysql://localhost:3306/RatedFilms", "root", "root");

        //Get url's images
        hrefList.subList(250, hrefList.size()).clear();
        for (String href : hrefList) {
            imageUrl = Urls.urlKinopoisk;
            imageUrl += "/images/film_big/" + getNumberToString(href) + ".jpg";
            imageUrlList.add(imageUrl);
        }

        //get page documents
        for (int i = 0; i < 6; i++) {
            System.out.println(i + " " + Urls.urlKinopoisk + hrefList.get(i));
            pageList.add(JsParser.getDocument(Urls.urlKinopoisk + hrefList.get(i)));
        }

        System.out.println(pageList.size());
        //get description
        JsParser jsParser = new JsParser();
        for (int i = 0; i < 6; i++) {
            description = jsParser.getElements(pageList.get(i), "div.brand_words[itemprop=description]").first().text();
            System.out.println(description);

        }

      /*  //update data in database
        for (int i = 0; i < 250; i++) {
            database.AddValue(connection, "Rated", "title, description, imgURL", titleList.get(i) + ", " + descriptionList.get(i) + ", " + imageUrlList.get(i));
            // database.UpdateValue(connection, "Rated", "description = \"" + descriptionList.get(i) + "\"", "id = " + (i + 1));
        }*/

        //connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
       // InitialDatabase();

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

        for (int i = 0; i < 6; i++) {
            System.out.println(i);
            documents.add(JsParser.getDocument(Urls.urlKinopoisk + hrefs.get(i)));
        }


        for (int i = 0; i < 6; i++) {
            descriptions.add(jsParser.getDescription(documents.get(i), "div.brand_words[itemprop=description]"));
            db.UpdateValue(connection, "Rated", "description = \"" + descriptions.get(i) + "\"", "id = " + (i + 1));
            System.out.println(descriptions.get(i));

        }
        for (int i = 0; i < 250; i++) {
            Database.AddValue(connection, "Rated", "title", titles.get(i));
        }
        for (Integer i = 0; i < 250; i++)
            System.out.println(hrefs.get(i));

    }

}
