package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/testjava";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "0707";
    private static Connection connection = null;
    
    public static Connection getConnection() {
        if (connection!=null) return connection;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException connectionException) {
            throw new RuntimeException("Error: Could not connect to the database!", connectionException);
        }
        return connection;
    }
}