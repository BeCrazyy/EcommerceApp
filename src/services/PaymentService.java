package services;

import models.Payment;
import models.PaymentStatus;

import java.util.HashMap;

public class PaymentService {

    private HashMap<String, Payment> paymentIdAndPaymentHashMap; // <PaymentId, Payment>

    public PaymentService() {
        this.paymentIdAndPaymentHashMap = new HashMap<>();
    }

    public Payment initiatePayment(Double amount, PaymentStatus paymentStatus) {
        Payment payment = new Payment(amount, paymentStatus);
        paymentIdAndPaymentHashMap.put(payment.getPaymentId(), payment);
        return payment;
    }

    public Payment getPayment(String paymentId) {
      Payment payment = paymentIdAndPaymentHashMap.get(paymentId);
      if(payment != null) {
        return payment;
      }

        // <todo> - make a ValidationException
        throw new RuntimeException("OrderId is not present");
    }

}
