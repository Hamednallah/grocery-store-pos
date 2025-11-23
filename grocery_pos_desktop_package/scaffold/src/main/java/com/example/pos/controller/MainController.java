package com.example.pos.controller;

import com.example.pos.dao.ProductDAO;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private VBox sideNav;

    @FXML
    private StackPane contentPane;

    private static final double NAV_EXPANDED_WIDTH = 200;
    private static final double NAV_COLLAPSED_WIDTH = 80;
    private static final double ANIMATION_DURATION = 300;

    private ProductDAO productDAO;
    private boolean navCollapsed = false;
    private final Map<Button, String> buttonTextMap = new HashMap<>();

    public MainController() {
        // No-argument constructor required by FXML
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @FXML
    private void initialize() {
        // Store original button text
        for (Node node : sideNav.getChildren()) {
            if (node instanceof Button button && button.getText() != null && !button.getText().isEmpty()) {
                buttonTextMap.put(button, button.getText());
            }
        }
        showDashboardView();
    }

    @FXML
    private void showProductsView() {
        loadView("/com/example/pos/view/ProductsView.fxml", (ProductsController controller) -> controller.setProductDAO(productDAO));
    }

    @FXML
    private void toggleNav() {
        navCollapsed = !navCollapsed;

        double targetWidth = navCollapsed ? NAV_COLLAPSED_WIDTH : NAV_EXPANDED_WIDTH;
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(sideNav.prefWidthProperty(), targetWidth);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(ANIMATION_DURATION), keyValue);
        timeline.getKeyFrames().add(keyFrame);

        if (navCollapsed) {
            for (Node node : sideNav.getChildren()) {
                if (node instanceof Button button && buttonTextMap.containsKey(button)) {
                    button.setText("");
                }
            }
        }

        timeline.setOnFinished(event -> {
            if (!navCollapsed) {
                for (Node node : sideNav.getChildren()) {
                    if (node instanceof Button button && buttonTextMap.containsKey(button)) {
                        button.setText(buttonTextMap.get(button));
                    }
                }
            }
        });

        timeline.play();
    }

    @FXML
    private void showDashboardView() {
        loadView("/com/example/pos/view/DashboardView.fxml");
    }

    @FXML
    private void showSalesView() {
        loadView("/com/example/pos/view/SalesView.fxml");
    }

    @FXML
    private void showInventoryView() {
        loadView("/com/example/pos/view/InventoryView.fxml");
    }

    @FXML
    private void showPurchaseOrdersView() {
        loadView("/com/example/pos/view/PurchaseOrdersView.fxml");
    }

    @FXML
    private void showExpensesView() {
        loadView("/com/example/pos/view/ExpensesView.fxml");
    }

    @FXML
    private void showCustomersView() {
        loadView("/com/example/pos/view/CustomersView.fxml");
    }

    @FXML
    private void showReportsView() {
        loadView("/com/example/pos/view/ReportsView.fxml");
    }

    @FXML
    private void showSettingsView() {
        loadView("/com/example/pos/view/SettingsView.fxml");
    }

    @FXML
    private void handleExit() {
        Platform.exit();
    }

    private void loadView(String fxmlPath) {
        loadView(fxmlPath, (Object controller) -> {});
    }

    private <T> void loadView(String fxmlPath, java.util.function.Consumer<T> controllerInitializer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node view = loader.load();
            T controller = loader.getController();
            controllerInitializer.accept(controller);
            contentPane.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
