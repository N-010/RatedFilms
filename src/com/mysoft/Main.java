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

    public static FilmInformation getFilmInformationById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = Database.getDatabaseConnect("jdbc:mysql://localhost:3306/RatedFilms", "root", "root");
        ResultSet resultSet = Database.getResultSet(connection, "SELECT * FROM Rated WHERE id=" + id);

        while (resultSet.next())
            return new FilmInformation(resultSet.getString("title"), resultSet.getString("description"), resultSet.getString("imgURL"), resultSet.getString("country"), resultSet.getString("producer"), Integer.parseInt(resultSet.getString("year")), resultSet.getInt("id"));

        return null;
    }

    public static List<FilmInformation> getListFimInformation() throws SQLException, ClassNotFoundException {
        int id = 1;
        List<FilmInformation> filmInformationList = new ArrayList<>();
        Connection connection = Database.getDatabaseConnect("jdbc:mysql://localhost:3306/RatedFilms", "root", "root");
        ResultSet resultSet = Database.getResultSet(connection, "SELECT * FROM Rated");

        while (resultSet.next()) {
            filmInformationList.add(new FilmInformation(resultSet.getString("title"), resultSet.getString("description"), resultSet.getString("imgURL"), resultSet.getString("country"), resultSet.getString("producer"), Integer.parseInt(resultSet.getString("year")), resultSet.getInt("id")));
        }

        connection.close();

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
        Document page;
        List<String> titleList = new ArrayList<>(); //= JsParser.getListWithTextByDocument(mainPage, "td", "a.all[href^=/film/");
        List<Integer> yearsList = new ArrayList<>();
        List<String> hrefList = JsParser.getListAttributeByElements(JsParser.getElements(mainPage, "td", "a.all[href^=/film/"), "href");
        List<Document> pageList = new ArrayList<>();
        List<String> imgPathList = new ArrayList<>();
        List<String> descriptionList = new ArrayList<>();
        List<String> producerList = new ArrayList<>();

        String title;

        int year;
        String producer;
        String description;

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
            //DownloadImages(imageUrl, dirImageSafe + numberImage + ".jpg");
            imgPathList.add(new String(shortPath + numberImage + ".jpg"));
        }


        //get page documents
        for (int i = 0; i < 250; i++) {
            System.out.println(i + " " + Urls.urlKinopoisk + hrefList.get(i));
            pageList.add(JsParser.getDocument(Urls.urlKinopoisk + hrefList.get(i)).body().ownerDocument());
            page = pageList.get(i);

            Database.AddValue(connection, "Rated", "title, description, imgURL, year, producer", "'" + JsParser.getTextByDocument(page, "#headerFilm .moviename-big") + "', '" + JsParser.getElements(page, "div.brand_words[itemprop=description]").first().text() + "', '" + imgPathList.get(i) + "', '" + Integer.parseInt(JsParser.getTextByDocument(page, "table.info a")) + "', '" + JsParser.getTextByDocument(page, "[itemprop=director]") + "'");
        }

        connection.close();
    }

    public static int getNumberLinesDatabase() throws SQLException, ClassNotFoundException {
        Connection connection = Database.getDatabaseConnect("jdbc:mysql://localhost:3306/RatedFilms", "root", "root");
        int numberLines = 0;

        ResultSet resultSet = Database.getResultSet(connection, "SELECT COUNT(*) FROM Rated");

        while (resultSet.next())
            numberLines = resultSet.getInt(1);

        return numberLines;

    }

    public static List<FilmInformation> getListFilmsByTitle(String title) throws SQLException, ClassNotFoundException {
        Connection connection = Database.getDatabaseConnect("jdbc:mysql://localhost:3306/RatedFilms", "root", "root");
        ResultSet resultSet = Database.getResultSet(connection, "SELECT * FROM Rated WHERE title like '%" + title + "%'");
        List<FilmInformation> filmInformationList = new ArrayList<>();

        while (resultSet.next())
            filmInformationList.add(new FilmInformation(resultSet.getString("title"), resultSet.getString("description"), resultSet.getString("imgURL"), resultSet.getString("country"), resultSet.getString("producer"), Integer.parseInt(resultSet.getString("year")), resultSet.getInt("id")));

        return filmInformationList;
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        // DownloadImages("http://www.kinopoisk.ru/images/film_big/8221.jpg", "/home/Programming/IdeaProjects/RatedFilms/web/img/Films img/8221.jpg");
        //InitialDatabase();

        Zerx zerx = new Zerx();

        System.out.println(zerx.getPlayerCode("ну погоди", "блабла"));

    }

}
