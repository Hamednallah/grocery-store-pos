package com.example.pos.controller;

import com.example.pos.dao.ProductDAO;

public class ProductsController {

    private ProductDAO productDAO;

    public ProductsController() {
        // No-argument constructor required by FXML
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
}
