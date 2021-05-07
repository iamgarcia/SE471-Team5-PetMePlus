package com.team5.petmeplus.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
        try {
            String DB_NAME = System.getenv("DB_NAME");
            String DB_USER = System.getenv("DB_USER");
            String DB_PASSWORD = System.getenv("DB_PASSWORD");
            String URL = "jdbc:mysql://localhost/" + DB_NAME;
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
