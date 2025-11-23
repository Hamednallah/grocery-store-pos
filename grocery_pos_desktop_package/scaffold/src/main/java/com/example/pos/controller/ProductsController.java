package com.example.pos.controller;

import com.example.pos.dao.ProductDAO;
import com.example.pos.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class ProductsController {

    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, String> skuColumn;
    @FXML
    private TableColumn<Product, String> barcodeColumn;
    @FXML
    private TableColumn<Product, String> nameArColumn;
    @FXML
    private TableColumn<Product, Double> sellPriceColumn;
    @FXML
    private TableColumn<Product, Double> qtyColumn;
    @FXML
    private TableColumn<Product, String> expiryDateColumn;

    @FXML
    private TextField skuField;
    @FXML
    private TextField barcodeField;
    @FXML
    private TextField nameArField;
    @FXML
    private TextField sellPriceField;
    @FXML
    private TextField qtyField;
    @FXML
    private DatePicker expiryDateField;

    private final ProductDAO productDAO;

    public ProductsController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @FXML
    public void initialize() {
        // Bind table columns to Product properties
        skuColumn.setCellValueFactory(new PropertyValueFactory<>("sku"));
        barcodeColumn.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        nameArColumn.setCellValueFactory(new PropertyValueFactory<>("nameAr"));
        sellPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        qtyColumn.setCellValueFactory(new PropertyValueFactory<>("qty"));
        expiryDateColumn.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));

        // Add listener to populate form when a product is selected
        productsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> populateForm(newValue)
        );

        loadProducts();
    }

    private void loadProducts() {
        productsTable.getItems().setAll(productDAO.getAllProducts());
    }

    private void populateForm(Product product) {
        if (product != null) {
            skuField.setText(product.getSku());
            barcodeField.setText(product.getBarcode());
            nameArField.setText(product.getNameAr());
            sellPriceField.setText(String.valueOf(product.getSellPrice()));
            qtyField.setText(String.valueOf(product.getQty()));
            if (product.getExpiryDate() != null) {
                expiryDateField.setValue(LocalDate.parse(product.getExpiryDate()));
            } else {
                expiryDateField.setValue(null);
            }
        }
    }

    private void clearForm() {
        skuField.clear();
        barcodeField.clear();
        nameArField.clear();
        sellPriceField.clear();
        qtyField.clear();
        expiryDateField.setValue(null);
        productsTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleNewProduct() {
        clearForm();
    }

    @FXML
    private void handleSaveProduct() {
        // Placeholder for save/update logic
        System.out.println("Save Product");
        loadProducts(); // Reload to reflect changes
    }

    @FXML
    private void handleDeleteProduct() {
        // Placeholder for delete logic
        System.out.println("Delete Product");
        loadProducts(); // Reload to reflect changes
    }
}
