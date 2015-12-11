package com.mysoft;

import org.jsoup.nodes.Document;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by root on 24.10.15.
 */

public class Main {

    public static void DownloadImages(String url, String path) throws IOException {
        /*BufferedImage bufferedImage = ImageIO.read(new URL(url));

        ImageIO.write(bufferedImage, "jpg", new File(path));*/
        URL url1 = new URL(url);
        InputStream is = url1.openStream();
        OutputStream os = new FileOutputStream(path);

        byte[] bytes = new byte[2048];
        int length;

        while ((length = is.read(bytes)) != -1) {
            os.write(bytes, 0, length);
        }

        is.close();
        os.close();
    }

    public static List<FilmInformation> getListFimInformation() throws SQLException, ClassNotFoundException {
        List<FilmInformation> filmInformationList = new ArrayList<>();
        Connection connection = Database.getDatabaseConnect("jdbc:mysql://localhost:3306/RatedFilms", "root", "root");
        ResultSet resultSet = Database.getResultSet(connection, "SELECT title, description, imgURL FROM Rated");

        while (resultSet.next()) {
            filmInformationList.add(new FilmInformation(resultSet.getString("title"), resultSet.getString("description"), resultSet.getString("imgURL")));
        }

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

    public static void InitialDatabase() throws SQLException, ClassNotFoundException, IOException {
        Document mainPage = JsParser.getDocument(Urls.urlKinopoiskParse);
        List<String> titleList = JsParser.getListWhithTextByDocument(mainPage, "td", "a.all[href^=/film/");
        List<String> hrefList = JsParser.getListAttributeByElements(JsParser.getElements(mainPage, "td", "a.all[href^=/film/"), "href");
        List<Document> pageList = new ArrayList<>();
        List<String> imgPathList = new ArrayList<>();
        List<String> descriptionList = new ArrayList<>();
        String dirImageSafe = "/home/Programming/IdeaProjects/RatedFilms/web/img/KinopoiskTop/";
        String shortPath = "/img/KinopoiskTop/";
        String imageUrl;
        String numberImage;
        Connection connection = Database.getDatabaseConnect("jdbc:mysql://localhost:3306/RatedFilms", "root", "root");

        //Get url's images
        hrefList.subList(250, hrefList.size()).clear();
        for (String href : hrefList) {
            numberImage = getNumberToString(href);
            imageUrl = Urls.urlKinopoisk;
            imageUrl += "/images/film_big/" + numberImage + ".jpg";
            System.out.println(imageUrl);
            DownloadImages(imageUrl, dirImageSafe + numberImage + ".jpg");
            imgPathList.add(new String(shortPath + numberImage + ".jpg"));
        }


        //get page documents
        for (int i = 0; i < 250; i++) {
            System.out.println(i + " " + Urls.urlKinopoisk + hrefList.get(i));
            pageList.add(JsParser.getDocument(Urls.urlKinopoisk + hrefList.get(i)).body().ownerDocument());
        }

        //get description
        for (int i = 0; i < 250; i++) {
            descriptionList.add(JsParser.getElements(pageList.get(i), "div.brand_words[itemprop=description]").first().text());

        }
        //System.out.println(titleList.get(0), descriptionList);

        //update data in database
        for (int i = 0; i < 250; i++) {
            Database.AddValue(connection, "Rated", "title, description, imgURL", "'" + titleList.get(i) + "'" + ", " + "'" + descriptionList.get(i) + "'" + ", " + "'" + imgPathList.get(i) + "'");
            // database.UpdateValue(connection, "Rated", "description = \"" + descriptionList.get(i) + "\"", "id = " + (i + 1));
        }

        connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        // DownloadImages("http://www.kinopoisk.ru/images/film_big/8221.jpg", "/home/Programming/IdeaProjects/RatedFilms/web/img/Films img/8221.jpg");

        InitialDatabase();
    }

}
