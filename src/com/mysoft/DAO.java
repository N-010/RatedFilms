package com.mysoft;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 25.10.15.
 */
public class DAO {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/RatedFilms", "root", "");

    }

    public static List<Post> posts;

    public static List<Post> getPosts() throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT id, txt from posts");

        ResultSet resultSet = ps.executeQuery("SELECT id, txt from posts");

        ArrayList<Post> posts = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String txt = resultSet.getString(2);
            posts.add(new Post(id, txt));
        }

        return posts;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.print(getPosts());
    }

    public static void deletePost(int id) {

    }

    public static void addPost(String text) {

    }
}
