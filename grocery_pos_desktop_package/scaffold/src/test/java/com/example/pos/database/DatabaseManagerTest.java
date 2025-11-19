package com.example.pos.database;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DatabaseManagerTest {

    @Test
    void testGetConnection() {
        assertDoesNotThrow(() -> {
            Connection connection = DatabaseManager.getConnection();
            assertNotNull(connection);
            connection.close();
        });
    }
}
