package com.example.pos.dao;

import com.example.pos.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public void addProduct(Product product) {
        String sql = "INSERT INTO products(name_ar, name_en, sku, barcode, category, unit, purchase_price_last, purchase_price_avg, sell_price, qty, expiry_date, expiry_warning_offset, low_stock_threshold, description, created_by) " +
                     "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, product.getNameAr());
            pstmt.setString(2, product.getNameEn());
            pstmt.setString(3, product.getSku());
            pstmt.setString(4, product.getBarcode());
            pstmt.setString(5, product.getCategory());
            pstmt.setString(6, product.getUnit());
            pstmt.setDouble(7, product.getPurchasePriceLast());
            pstmt.setDouble(8, product.getPurchasePriceAvg());
            pstmt.setDouble(9, product.getSellPrice());
            pstmt.setDouble(10, product.getQty());
            pstmt.setString(11, product.getExpiryDate());
            pstmt.setString(12, product.getExpiryWarningOffset());
            pstmt.setDouble(13, product.getLowStockThreshold());
            pstmt.setString(14, product.getDescription());
            pstmt.setInt(15, product.getCreatedBy());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                product.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product getProduct(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapRowToProduct(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(mapRowToProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void updateProduct(Product product) {
        String sql = "UPDATE products SET name_ar = ?, name_en = ?, sku = ?, barcode = ?, category = ?, unit = ?, " +
                     "purchase_price_last = ?, purchase_price_avg = ?, sell_price = ?, qty = ?, expiry_date = ?, " +
                     "expiry_warning_offset = ?, low_stock_threshold = ?, description = ?, updated_at = datetime('now') " +
                     "WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, product.getNameAr());
            pstmt.setString(2, product.getNameEn());
            pstmt.setString(3, product.getSku());
            pstmt.setString(4, product.getBarcode());
            pstmt.setString(5, product.getCategory());
            pstmt.setString(6, product.getUnit());
            pstmt.setDouble(7, product.getPurchasePriceLast());
            pstmt.setDouble(8, product.getPurchasePriceAvg());
            pstmt.setDouble(9, product.getSellPrice());
            pstmt.setDouble(10, product.getQty());
            pstmt.setString(11, product.getExpiryDate());
            pstmt.setString(12, product.getExpiryWarningOffset());
            pstmt.setDouble(13, product.getLowStockThreshold());
            pstmt.setString(14, product.getDescription());
            pstmt.setInt(15, product.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Product mapRowToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setSku(rs.getString("sku"));
        product.setBarcode(rs.getString("barcode"));
        product.setNameAr(rs.getString("name_ar"));
        product.setNameEn(rs.getString("name_en"));
        product.setCategory(rs.getString("category"));
        product.setUnit(rs.getString("unit"));
        product.setPurchasePriceLast(rs.getDouble("purchase_price_last"));
        product.setPurchasePriceAvg(rs.getDouble("purchase_price_avg"));
        product.setSellPrice(rs.getDouble("sell_price"));
        product.setQty(rs.getDouble("qty"));
        product.setExpiryDate(rs.getString("expiry_date"));
        product.setExpiryWarningOffset(rs.getString("expiry_warning_offset"));
        product.setLowStockThreshold(rs.getDouble("low_stock_threshold"));
        product.setDescription(rs.getString("description"));
        product.setCreatedBy(rs.getInt("created_by"));
        product.setCreatedAt(rs.getString("created_at"));
        product.setUpdatedAt(rs.getString("updated_at"));
        return product;
    }
}
