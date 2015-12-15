package com.mysoft;

import java.sql.*;
import java.util.Properties;

/**
 * Created by root on 09.12.15.
 */
public class Database {
    public static Connection getDatabaseConnect(String url, String username, String password) throws SQLException, ClassNotFoundException {
        Properties properties = new Properties();

        Class.forName("com.mysql.jdbc.Driver");
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "UTF-8");

        return DriverManager.getConnection(url, properties);
    }

    public static void ExecuteQuery(Connection connection, String query) throws SQLException {
        Statement statement;

        statement = connection.createStatement();
        statement.execute(query);

        if (statement != null)
            statement.close();
    }

    public static void addValue(Connection connection, String table, String column, String value) throws SQLException {
        String query = "INSERT INTO " + table + "(" + column + ")" + " VALUE (" + value + ")";
        Statement statement = connection.createStatement();

        statement.execute(query);
        statement.close();
    }

    public static void UpdateValue(Connection connection, String table, String value, String where) throws SQLException {
        String query = "UPDATE " + table + " SET " + value + " WHERE " + where;
        Statement statement = connection.createStatement();

        statement.execute(query);
        statement.close();
    }

    public static ResultSet getResultSet(Connection connection, String query) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery(query);

        return resultSet;
    }

    public static int getNumberLinesDatabase() throws SQLException, ClassNotFoundException {
        Connection connection = Database.getDatabaseConnect("jdbc:mysql://localhost:3306/RatedFilms", "root", "root");
        int numberLines = 0;

        ResultSet resultSet = Database.getResultSet(connection, "SELECT COUNT(*) FROM Rated");

        while (resultSet.next())
            numberLines = resultSet.getInt(1);

        return numberLines;
    }

}
