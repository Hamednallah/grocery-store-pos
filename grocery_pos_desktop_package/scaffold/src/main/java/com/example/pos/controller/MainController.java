package com.example.pos.controller;

import com.example.pos.dao.ProductDAO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private VBox sideNav;

    @FXML
    private StackPane contentPane;

    private final ProductDAO productDAO;

    public MainController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @FXML
    private void initialize() {
        showDashboardView();
    }

    @FXML
    private void showProductsView() {
        loadView("/com/example/pos/view/ProductsView.fxml", param -> new ProductsController(productDAO));
    }

    @FXML
    private void toggleNav() {
        sideNav.setVisible(!sideNav.isVisible());
        sideNav.setManaged(!sideNav.isManaged());
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

    private void loadView(String fxmlPath) {
        loadView(fxmlPath, null);
    }

    private void loadView(String fxmlPath, javafx.util.Callback<Class<?>, Object> controllerFactory) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            if (controllerFactory != null) {
                loader.setControllerFactory(controllerFactory);
            }
            Node view = loader.load();
            contentPane.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
