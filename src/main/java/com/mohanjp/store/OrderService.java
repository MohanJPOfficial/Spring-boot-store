package com.mohanjp.store;

import com.mohanjp.store.paymentService.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder(Double amount) {
        paymentService.processPayment(amount);
    }
}
