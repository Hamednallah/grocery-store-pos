package com.example.pos.model;

public class Product {
    private int id;
    private String sku;
    private String barcode;
    private String nameAr;
    private String nameEn;
    private String category;
    private String unit;
    private double purchasePriceLast;
    private double purchasePriceAvg;
    private double sellPrice;
    private double qty;
    private String expiryDate;
    private String expiryWarningOffset;
    private double lowStockThreshold;
    private String description;
    private int createdBy;
    private String createdAt;
    private String updatedAt;

    // Getters and setters for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPurchasePriceLast() {
        return purchasePriceLast;
    }

    public void setPurchasePriceLast(double purchasePriceLast) {
        this.purchasePriceLast = purchasePriceLast;
    }

    public double getPurchasePriceAvg() {
        return purchasePriceAvg;
    }

    public void setPurchasePriceAvg(double purchasePriceAvg) {
        this.purchasePriceAvg = purchasePriceAvg;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getExpiryWarningOffset() {
        return expiryWarningOffset;
    }

    public void setExpiryWarningOffset(String expiryWarningOffset) {
        this.expiryWarningOffset = expiryWarningOffset;
    }

    public double getLowStockThreshold() {
        return lowStockThreshold;
    }

    public void setLowStockThreshold(double lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
