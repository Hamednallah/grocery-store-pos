package com.example.pos.dao;

import com.example.pos.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setFullName(rs.getString("full_name"));
                user.setRole(rs.getString("role"));
                user.setCreatedAt(rs.getString("created_at"));
                user.setUpdatedAt(rs.getString("updated_at"));
                return user;
            }
        } catch (SQLException e) {
            // In a real application, you would log this exception
            e.printStackTrace();
        }
        return null;
    }
}
