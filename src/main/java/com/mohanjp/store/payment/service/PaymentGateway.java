package com.mohanjp.store.payment.service;

import com.mohanjp.store.order.entity.OrderEntity;

import java.util.Optional;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(OrderEntity orderEntity);
    Optional<PaymentResult> parseWebhookRequest(WebhookRequest webhookRequest);
}
