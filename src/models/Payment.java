package models;

import java.util.Date;
import java.util.UUID;

public class Payment {
    private final String paymentId;
    private PaymentStatus paymentStatus;
    private Double amount;
    private final Date timestamp;

    public Payment(Double amount, PaymentStatus paymentStatus) {
        this.paymentId = UUID.randomUUID().toString();
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.timestamp = new Date();
    }

    public String getPaymentId() {
        return paymentId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}
