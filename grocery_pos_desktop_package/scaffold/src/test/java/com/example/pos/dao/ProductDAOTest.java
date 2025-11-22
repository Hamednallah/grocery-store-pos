package com.example.pos.dao;

import com.example.pos.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDAOTest {

    private ProductDAO productDAO;

    @BeforeEach
    void setUp() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:");
        productDAO = new ProductDAO(connection);

        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE products (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "sku TEXT, barcode TEXT, name_ar TEXT NOT NULL, name_en TEXT, " +
                    "category TEXT, unit TEXT, purchase_price_last REAL, purchase_price_avg REAL, " +
                    "sell_price REAL, qty REAL, expiry_date TEXT, expiry_warning_offset TEXT, " +
                    "low_stock_threshold REAL, description TEXT, created_by INTEGER, " +
                    "created_at TEXT, updated_at TEXT)");
        }
    }

    @Test
    void testAddAndGetProduct() {
        Product product = new Product();
        product.setNameAr("تفاح");
        product.setSellPrice(10.0);
        productDAO.addProduct(product);

        Product retrievedProduct = productDAO.getProduct(1);
        assertNotNull(retrievedProduct);
        assertEquals("تفاح", retrievedProduct.getNameAr());
    }

    @Test
    void testGetAllProducts() {
        Product p1 = new Product();
        p1.setNameAr("تفاح");
        productDAO.addProduct(p1);

        Product p2 = new Product();
        p2.setNameAr("برتقال");
        productDAO.addProduct(p2);

        List<Product> products = productDAO.getAllProducts();
        assertEquals(2, products.size());
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setNameAr("تفاح");
        productDAO.addProduct(product);

        product.setId(1);
        product.setNameAr("تفاح أخضر");
        productDAO.updateProduct(product);

        Product updatedProduct = productDAO.getProduct(1);
        assertEquals("تفاح أخضر", updatedProduct.getNameAr());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setNameAr("تفاح");
        productDAO.addProduct(product);

        productDAO.deleteProduct(1);
        assertNull(productDAO.getProduct(1));
    }
}
