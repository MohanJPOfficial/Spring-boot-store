package com.mohanjp.store.service.paymentGateway;

import com.mohanjp.store.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentResult {
    private long orderId;
    private PaymentStatus paymentStatus;
}
