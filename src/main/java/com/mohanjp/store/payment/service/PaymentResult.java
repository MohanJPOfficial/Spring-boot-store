package com.mohanjp.store.payment.service;

import com.mohanjp.store.order.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentResult {
    private long orderId;
    private PaymentStatus paymentStatus;
}
