package com.example.pos.controller;

import com.example.pos.dao.ProductDAO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane mainPane;

    private final ProductDAO productDAO;

    public MainController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @FXML
    private void handleClose() {
        Platform.exit();
    }

    @FXML
    private void showProductsView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pos/view/ProductsView.fxml"));
            loader.setControllerFactory(param -> new ProductsController(productDAO));
            mainPane.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
