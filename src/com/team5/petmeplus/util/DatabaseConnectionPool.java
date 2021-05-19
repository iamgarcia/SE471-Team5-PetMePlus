package com.team5.petmeplus.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionPool extends ObjectPool<Connection> {
    private static DatabaseConnectionPool instance;

    private final String DB_NAME;
    private final String DB_USER;
    private final String DB_PASSWORD;
    private final String URL;

    private DatabaseConnectionPool() {
        super();

        DB_NAME = System.getenv("DB_NAME");
        DB_USER = System.getenv("DB_USER");
        DB_PASSWORD = System.getenv("DB_PASSWORD");
        URL = "jdbc:mysql://localhost/" + DB_NAME;
    }

    public static DatabaseConnectionPool getInstance() {
        if(instance == null) {
            instance = new DatabaseConnectionPool();
        }

        return instance;
    }

    @Override
    protected Connection create() {
        try {
            return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean validate(Connection conn) {
        try {
            return (!conn.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void expire(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
