package com.example.pos;

import atlantafx.base.theme.PrimerLight;
import com.example.pos.controller.LoginController;
import com.example.pos.controller.MainController;
import com.example.pos.dao.ProductDAO;
import com.example.pos.dao.UserDAO;
import com.example.pos.database.DatabaseManager;
import com.example.pos.database.SchemaInitializer;
import com.example.pos.service.AuthService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainApp extends Application {

    private Stage primaryStage;
    private Connection connection;
    private AuthService authService;
    private ResourceBundle bundle;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        // Apply AtlantaFX theme
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());

        // Initialize database
        this.connection = DatabaseManager.getConnection();
        SchemaInitializer.initialize(connection);

        // Initialize backend services
        UserDAO userDAO = new UserDAO(connection);
        this.authService = new AuthService(userDAO);

        // Setup i18n
        Locale.setDefault(new Locale("ar"));
        this.bundle = ResourceBundle.getBundle("i18n", Locale.getDefault());

        showLoginScreen();
    }

    public void showLoginScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pos/view/LoginView.fxml"), bundle);
        loader.setControllerFactory(param -> new LoginController(this, authService, bundle));

        Parent root = loader.load();
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle(bundle.getString("app.title"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showMainView() throws IOException {
        ProductDAO productDAO = new ProductDAO(connection);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pos/view/MainView.fxml"), bundle);
        loader.setControllerFactory(param -> new MainController() {{
            setProductDAO(productDAO);
        }});

        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle(bundle.getString("app.title"));
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
