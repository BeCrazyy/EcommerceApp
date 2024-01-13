package services;

import models.ShoppingCart;

import java.util.HashMap;

public class ShoppingCartService {
    private ProductService productService = new ProductService();
    private UserService userService = new UserService();

    private HashMap<String, ShoppingCart> shoppingCartHashMap; // <userId, ShoppingCart>

    public ShoppingCartService() {
        this.shoppingCartHashMap = new HashMap<>();
    }

    // Once user register first time then initialise it shopping cart
    public void initiateShoppingCart(String userId) {
     ShoppingCart shoppingCart = new ShoppingCart(userId);
        shoppingCartHashMap.put(userId, shoppingCart);
    }

    // return <productId, quantity>
    public HashMap<String, Integer> getShoppingCart(String userId) {
      ShoppingCart shoppingCart = shoppingCartHashMap.get(userId);
      if(shoppingCart != null) {
        return shoppingCart.getProductIDAndQuantityHashMap();
      }

        // userId is invalid
        // <todo> - make a ValidationException
        throw new RuntimeException("Shopping Cart is not present");
    }

    public void addToCart(String userId, String productId, Integer quantity) {
        productService.getProduct(productId);
        userService.getUser(userId);
        ShoppingCart shoppingCart = shoppingCartHashMap.get(userId);

        if(shoppingCart != null) {
           HashMap<String, Integer> productIDAndQuantityHashMap = shoppingCart.getProductIDAndQuantityHashMap();
           Integer currentQuantity = productIDAndQuantityHashMap.get(productId);
           if(currentQuantity == null) currentQuantity = 0;
           quantity += currentQuantity;
           productIDAndQuantityHashMap.put(productId, quantity);
        }

        // shopping cart not exists for given userId
        // <todo> - make a ValidationException
        throw new RuntimeException("Shopping Cart is not present for given userId");
    }

    public Double getCartTotalAmount(String userId) {
        HashMap<String, Integer> productIDsAndQuantityHashMap = getShoppingCart(userId);
        Double totalAmount = 0.0;

        for (String productId : productIDsAndQuantityHashMap.keySet()) {
            Integer quantity = productIDsAndQuantityHashMap.get(productId);
            Double price = productService.getProduct(productId).getPrice();
            totalAmount += (quantity * price);
        }
        return totalAmount;
    }

    public void emptyCart(String userId) {
        // <todo> check for userId i.e exists or not
        shoppingCartHashMap.remove(userId);
    }
}
