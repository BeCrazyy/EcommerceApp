package models;

import java.util.HashMap;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final String userId;
    private HashMap<String, Integer> productIDAndQuantityHashMap;
    private final String paymentId;
    private ShipmentStatus shipmentStatus;

    public Order(String userId, HashMap<String, Integer> productIDAndQuantityHashMap, String paymentId) {
        this.orderId = UUID.randomUUID().toString(); // auto-generated
        this.userId = userId;
        this.productIDAndQuantityHashMap = productIDAndQuantityHashMap;
        this.paymentId = paymentId;
        this.shipmentStatus = ShipmentStatus.IN_PROGRESS;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }
}
