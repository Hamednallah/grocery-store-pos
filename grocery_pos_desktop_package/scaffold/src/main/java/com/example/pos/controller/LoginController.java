package com.example.pos.controller;

import com.example.pos.service.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    private final AuthService authService;
    private final ResourceBundle resourceBundle;

    public LoginController(AuthService authService, ResourceBundle resourceBundle) {
        this.authService = authService;
        this.resourceBundle = resourceBundle;
    }

    @FXML
    protected void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (authService.login(username, password)) {
            statusLabel.setText(resourceBundle.getString("login.success"));
            statusLabel.setStyle("-fx-text-fill: green;");
        } else {
            statusLabel.setText(resourceBundle.getString("login.failure"));
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }
}
