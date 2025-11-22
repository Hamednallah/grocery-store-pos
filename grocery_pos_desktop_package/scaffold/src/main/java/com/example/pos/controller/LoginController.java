package com.example.pos.controller;

import com.example.pos.MainApp;
import com.example.pos.service.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    private final MainApp mainApp;
    private final AuthService authService;
    private final ResourceBundle resourceBundle;

    public LoginController(MainApp mainApp, AuthService authService, ResourceBundle resourceBundle) {
        this.mainApp = mainApp;
        this.authService = authService;
        this.resourceBundle = resourceBundle;
    }

    @FXML
    protected void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (authService.login(username, password)) {
            try {
                mainApp.showMainView();
            } catch (IOException e) {
                e.printStackTrace();
                statusLabel.setText("Error loading main view.");
                statusLabel.setStyle("-fx-text-fill: red;");
            }
        } else {
            statusLabel.setText(resourceBundle.getString("login.failure"));
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }
}
