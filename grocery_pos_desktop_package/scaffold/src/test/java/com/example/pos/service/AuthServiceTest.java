package com.example.pos.service;

import com.example.pos.dao.UserDAO;
import com.example.pos.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthServiceTest {
    private AuthService authService;
    private UserDAO userDAO;

    @BeforeEach
    void setUp() {
        userDAO = mock(UserDAO.class);
        authService = new AuthService(userDAO);
    }

    @Test
    void testLogin_success() {
        User user = new User();
        user.setUsername("admin");
        user.setPasswordHash(BCrypt.hashpw("password", BCrypt.gensalt()));
        when(userDAO.findByUsername("admin")).thenReturn(user);

        assertTrue(authService.login("admin", "password"));
    }

    @Test
    void testLogin_failure_wrongPassword() {
        User user = new User();
        user.setUsername("admin");
        user.setPasswordHash(BCrypt.hashpw("password", BCrypt.gensalt()));
        when(userDAO.findByUsername("admin")).thenReturn(user);

        assertFalse(authService.login("admin", "wrongpassword"));
    }

    @Test
    void testLogin_failure_userNotFound() {
        when(userDAO.findByUsername("nonexistent")).thenReturn(null);
        assertFalse(authService.login("nonexistent", "password"));
    }
}
