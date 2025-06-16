package com.mohanjp.store.paymentService;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("paypal")
@Primary
public class PaypalPaymentService implements PaymentService {

    @Override
    public void processPayment(double amount) {
        System.out.println("Paypal:");
        System.out.println("Amount: " + amount);
    }
}
