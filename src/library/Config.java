package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    private static final String url = "jdbc:mysql://localhost:3306/library";
    private static final String username = "root";
    private static final String password = "";

    public static Connection getConnection() throws SQLException {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error: MySQL JDBC driver not found.");
            throw new SQLException("JDBC driver not found.", e);
        }
    }
}
