package models;

import java.util.UUID;

public class Product {
    private final String productId;
    private String productName;
    private String description;
    private Double price;
    private Integer stockQuantity;

    public Product(String productName, String description, Double price, Integer stockQuantity) {
        this.productId = UUID.randomUUID().toString(); // auto-generated
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
