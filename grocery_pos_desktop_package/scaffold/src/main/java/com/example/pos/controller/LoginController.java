package com.example.pos.controller;

<<<<<<< HEAD
=======
import com.example.pos.service.AuthService;
>>>>>>> fix/login-flow-and-db-init
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
<<<<<<< HEAD
=======
import java.util.ResourceBundle;
>>>>>>> fix/login-flow-and-db-init

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

<<<<<<< HEAD
    @FXML
    protected void handleLoginButtonAction() {
        // Will be implemented later
=======
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
>>>>>>> fix/login-flow-and-db-init
    }
}
