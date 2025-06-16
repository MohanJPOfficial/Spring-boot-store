package com.mohanjp.store;

import com.mohanjp.store.paymentService.PaymentService;
import com.mohanjp.store.paymentService.PaypalPaymentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(@Qualifier("paypal") PaypalPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder(Double amount) {
        paymentService.processPayment(amount);
    }
}
