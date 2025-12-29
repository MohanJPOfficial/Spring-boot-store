package com.mohanjp.store.service.paymentGateway;

import com.mohanjp.store.entity.OrderEntity;
import com.mohanjp.store.service.CheckoutSession;
import java.util.Optional;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(OrderEntity orderEntity);
    Optional<PaymentResult> parseWebhookRequest(WebhookRequest webhookRequest);
}
