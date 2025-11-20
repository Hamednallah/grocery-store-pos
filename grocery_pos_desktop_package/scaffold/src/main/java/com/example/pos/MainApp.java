package com.example.pos;

import com.example.pos.controller.LoginController;
import com.example.pos.dao.UserDAO;
import com.example.pos.database.DatabaseManager;
import com.example.pos.database.SchemaInitializer;
import com.example.pos.service.AuthService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(new Locale("ar"));
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", Locale.getDefault());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pos/view/LoginView.fxml"), bundle);
        // Initialize database
        Connection connection = DatabaseManager.getConnection();
        SchemaInitializer.initialize(connection);

        // Initialize backend services
        UserDAO userDAO = new UserDAO(connection);
        AuthService authService = new AuthService(userDAO);
        Locale.setDefault(new Locale("ar"));

        // Setup FXML Loader with a controller factory for dependency injection
        loader.setControllerFactory(param -> new LoginController(authService, bundle));

        Parent root = loader.load();

        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        primaryStage.setTitle(bundle.getString("app.title"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
