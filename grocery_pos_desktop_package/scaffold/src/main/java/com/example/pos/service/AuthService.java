package com.example.pos.service;

import com.example.pos.dao.UserDAO;
import com.example.pos.model.User;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {
    private final UserDAO userDAO;

    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean login(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user != null) {
            return BCrypt.checkpw(password, user.getPasswordHash());
        }
        return false;
    }
}
