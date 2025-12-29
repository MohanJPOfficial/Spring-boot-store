package com.mohanjp.store.payment.service;

import com.mohanjp.store.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentResult {
    private long orderId;
    private PaymentStatus paymentStatus;
}
