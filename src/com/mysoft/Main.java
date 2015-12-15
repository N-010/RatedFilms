package com.mysoft;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by root on 24.10.15.
 */

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        // DownloadImages("http://www.kinopoisk.ru/images/film_big/8221.jpg", "/home/Programming/IdeaProjects/RatedFilms/web/img/Films img/8221.jpg");
        Kinopoisk.InitialDatabase();
    }

}
