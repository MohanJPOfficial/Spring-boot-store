package com.mohanjp.store.paymentService;

public class PaypalPaymentService implements PaymentService {

    @Override
    public void processPayment(double amount) {
        System.out.println("Paypal:");
        System.out.println("Amount: " + amount);
    }
}
