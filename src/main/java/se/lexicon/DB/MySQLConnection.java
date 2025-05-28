package se.lexicon.DB;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// You can add @Component and @Scope("singleton") instead of using @Bean to get the same effect.
// @Scope("singleton") is used to ensure only one instans is created, which @Beans does currently and is needed in this case.
//@Component
//@Scope("singleton")
public class MySQLConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/todoit";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
    }
}
