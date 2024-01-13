package services;

import models.Product;
import models.Payment;
import models.Order;
import models.PaymentStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class OrderService {
    private final ShoppingCartService shoppingCartService = new ShoppingCartService();

    private final ProductService productService = new ProductService();

    private final PaymentService paymentService = new PaymentService();

    // <todo> optimse it
    // <userId, <orderId, Order>>
    private HashMap<String, HashSet<Order>> userIdOrderIdSetHashmap; // <UserId, HashSet<OrderId>>
    private HashMap<String, Order> orderIdAndOrderHashMap; // <OrderId, Order>

    public List<Order> getOrderHistory(String userId) {
        HashSet<Order> orderIDsSet = userIdOrderIdSetHashmap.get(userId);

        if(orderIDsSet != null) {
            new ArrayList<>(orderIDsSet);
        }

        // <todo> - make a ValidationException
        throw new RuntimeException("Orders is not present");
    }

    public Order trackOrder(String userId, String orderId) {
        Order order = orderIdAndOrderHashMap.get(orderId);
        if(order == null) {
            // <todo> - make a ValidationException
            throw new RuntimeException("OrderId is not present");
        }

        if(order.getUserId() != userId) {
            // <todo> - make a ValidationException
            throw new RuntimeException("Invalid Access!!");
        }
        return order;
    }

    public Order checkout(String userId) {
        // fetching user cart
        HashMap<String, Integer> productIDsAndQuantityHashMap = shoppingCartService.getShoppingCart(userId);

        // check whether products its in stock or not
        for (String productId : productIDsAndQuantityHashMap.keySet()) {
            Product product = productService.getProduct(productId);
            Integer cartQuantity = productIDsAndQuantityHashMap.get(productId);
            if(product.getStockQuantity() < cartQuantity) {
              throw new RuntimeException("Product is Out of Stock : " + productId);
            }
        }

        Double totalAmount = shoppingCartService.getCartTotalAmount(userId);

        // initiate a payment
        Payment payment = paymentService.initiatePayment(totalAmount, PaymentStatus.COMPLETED);

        if(payment.getPaymentStatus() == PaymentStatus.COMPLETED) {

            // update the inventory
            for (String productId : productIDsAndQuantityHashMap.keySet()) {
                Product product = productService.getProduct(productId);
                Integer cartQuantity = productIDsAndQuantityHashMap.get(productId);
                Integer remaining = product.getStockQuantity() - cartQuantity;
                product.setStockQuantity(remaining);
            }

            // P1, .... P5
            // create an order
            Order successfulOrder = new Order(userId, productIDsAndQuantityHashMap, payment.getPaymentId());
            orderIdAndOrderHashMap.put(successfulOrder.getOrderId(), successfulOrder);

            Set<Order>ordersSet = userIdOrderIdSetHashmap.get(userId);
            ordersSet.add(successfulOrder);
            userIdOrderIdSetHashmap.put(userId, ordersSet);

            // clear the cart for the userId
            shoppingCartService.emptyCart(userId);

            return successfulOrder;
        }

        return new Order(userId, productIDsAndQuantityHashMap, payment.getPaymentId());
    }
}

