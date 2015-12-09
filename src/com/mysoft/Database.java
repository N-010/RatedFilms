package com.mysoft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by root on 09.12.15.
 */
public class Database {
    private String url;
    private String username;
    private String password;
    Connection connection;

    /*Database(String url, String username, String password) throws ClassNotFoundException, SQLException {
        this.url = url;
        this.username = username;
        this.password = password;
    }*/

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

    public static void AddValue(Connection connection, String column, String value) throws SQLException {
        String query = "INSERT INTO Rated(" + column + ")" + "VALUE ('" + value + "')";
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

}
