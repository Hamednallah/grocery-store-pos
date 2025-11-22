package com.example.pos.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:data/pos.db";

    public static Connection getConnection() throws SQLException {
        // Ensure the data directory exists
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        return DriverManager.getConnection(DATABASE_URL);
    }
}
