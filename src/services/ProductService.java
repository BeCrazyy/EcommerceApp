package services;

import models.Product;

import java.util.HashMap;

public class ProductService {
    private HashMap<String, Product> productHashMap; // <productId, Product>

    public Product getProduct(String productId) {
       Product product = productHashMap.get(productId);
       if(product != null) {
         return product;
       }

       // <todo> - make a ValidationException
       throw new RuntimeException("Product is not present");
    }

    public Product CreateProduct(String productName, String description, Double price, Integer stockQuantity) {
       validatePrice(price);
       Product newProduct = new Product(productName, description, price, stockQuantity);
        productHashMap.put(newProduct.getProductId(), newProduct);
       return newProduct;
    }

    private void validatePrice(Double price) throws RuntimeException {
        // <todo> - make a ValidationException
        if(price < 0) throw new RuntimeException("Price should be non-zero");
        // can write more validations
    }
}
