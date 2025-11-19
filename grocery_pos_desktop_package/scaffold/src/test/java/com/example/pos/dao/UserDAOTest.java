package com.example.pos.dao;

import com.example.pos.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserDAOTest {
    private UserDAO userDAO;

    @BeforeEach
    void setUp() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:");
        userDAO = new UserDAO(connection);

        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE users (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         "username TEXT UNIQUE NOT NULL, " +
                         "password_hash TEXT NOT NULL, " +
                         "full_name TEXT, " +
                         "role TEXT NOT NULL, " +
                         "created_at TEXT NOT NULL DEFAULT (datetime('now')), " +
                         "updated_at TEXT)");
            stmt.execute("INSERT INTO users (username, password_hash, full_name, role) VALUES " +
                         "('admin', '$2a$10$JWi7Oq541Qe/7AuSISwepOI8Y1Kjr1w8vlwU3B1X9fUHvZVQQoDcu', 'Admin User', 'Admin')");
        }
    }

    @Test
    void testFindByUsername_existingUser() {
        User user = userDAO.findByUsername("admin");
        assertNotNull(user);
        assertEquals("admin", user.getUsername());
    }

    @Test
    void testFindByUsername_nonExistingUser() {
        User user = userDAO.findByUsername("nonexistent");
        assertNull(user);
    }
}
