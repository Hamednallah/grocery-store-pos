package com.example.pos.controller;

import com.example.pos.dao.ProductDAO;

public class ProductsController {

    private final ProductDAO productDAO;

    public ProductsController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
}
