package se.lexicon.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO: Get the URL, USER and PASSWORD from property file
public class MySQLConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/todoit";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
    }
}
