package com.example.pos.controller;

import com.example.pos.dao.ProductDAO;
import com.example.pos.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductsController {

    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, String> nameArColumn;
    @FXML
    private TableColumn<Product, Double> sellPriceColumn;
    @FXML
    private TableColumn<Product, Double> qtyColumn;

    private final ProductDAO productDAO;

    public ProductsController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @FXML
    public void initialize() {
        nameArColumn.setCellValueFactory(new PropertyValueFactory<>("nameAr"));
        sellPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        qtyColumn.setCellValueFactory(new PropertyValueFactory<>("qty"));
        loadProducts();
    }

    private void loadProducts() {
        productsTable.getItems().setAll(productDAO.getAllProducts());
    }

    @FXML
    private void handleAddProduct() {
        System.out.println("Add Product");
    }

    @FXML
    private void handleEditProduct() {
        System.out.println("Edit Product");
    }

    @FXML
    private void handleDeleteProduct() {
        System.out.println("Delete Product");
    }
}
