package models;

import java.util.HashMap;

public class ShoppingCart {
    private final String userId;
    private HashMap<String, Integer> productIDsAndQuantityHashMap; // <productId, quantity>

    public ShoppingCart(String userId) {
        this.userId = userId;
        productIDsAndQuantityHashMap = new HashMap<>();
    }

    public String getUserId() {
        return userId;
    }

    public HashMap<String, Integer> getProductIDAndQuantityHashMap() {
        return productIDsAndQuantityHashMap;
    }
}
