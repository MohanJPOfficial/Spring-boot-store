package com.mohanjp.store.service.paymentGateway;

import com.mohanjp.store.entity.OrderEntity;
import com.mohanjp.store.service.CheckoutSession;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(OrderEntity orderEntity);
}
