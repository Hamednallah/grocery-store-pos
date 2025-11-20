package com.example.pos.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaInitializer {

    public static void initialize(Connection connection) {
        try (Statement stmt = connection.createStatement()) {
            String sql = new String(Files.readAllBytes(Paths.get("migrations/V1__init.sql")));
            stmt.executeUpdate(sql);
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Could not initialize database schema", e);
        }
    }
}
