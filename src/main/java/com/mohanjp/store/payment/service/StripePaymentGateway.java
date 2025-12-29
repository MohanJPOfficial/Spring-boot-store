package com.mohanjp.store.payment.service;

import com.mohanjp.store.entity.OrderEntity;
import com.mohanjp.store.entity.OrderItemEntity;
import com.mohanjp.store.entity.PaymentStatus;
import com.mohanjp.store.payment.exception.PaymentException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class StripePaymentGateway implements PaymentGateway {

    @Value("${websiteUrl}")
    private String websiteUrl;

    @Value("${stripe.webhookSecretKey}")
    private String webhookSecretKey;

    @Override
    public CheckoutSession createCheckoutSession(OrderEntity order) {
        try {
            // Create a checkout session
            var builder = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(websiteUrl + "/checkout-success?orderId=" + order.getId())
                    .setCancelUrl(websiteUrl + "/checkout-cancel")
                    .putMetadata("order_id", String.valueOf(order.getId()));

            order.getItems().forEach(item -> {
                var lineItem = createLineItem(item);
                builder.addLineItem(lineItem);
            });

            var session = Session.create(builder.build());
            return new CheckoutSession(session.getUrl());

        } catch (StripeException ex) {
            System.out.println(ex.getMessage());
            throw new PaymentException(ex.getMessage());
        }
    }

    @Override
    public Optional<PaymentResult> parseWebhookRequest(WebhookRequest webhookRequest) {
        try {
            var payload = webhookRequest.getPayload();
            var signature = webhookRequest.getHeaders().get("stripe-signature");
            var event = Webhook.constructEvent(payload, signature, webhookSecretKey);

            System.out.println("event: " + event.getType());

            return switch (event.getType()) {
                case "payment_intent.succeeded" ->
                    Optional.of(new PaymentResult(extractOrderId(event), PaymentStatus.PAID));

                case "payment_intent.payment_failed" ->
                    Optional.of(new PaymentResult(extractOrderId(event), PaymentStatus.FAILED));

                default -> Optional.empty();
            };
        } catch (SignatureVerificationException ex) {
            throw new PaymentException("Invalid signature: " + ex.getMessage());
        }
    }

    private long extractOrderId(Event event) {
        var stripeObject = event.getDataObjectDeserializer().getObject().orElseThrow(
            () -> new PaymentException("Unable to deserialize event object. Check the SDK and API version")
        );
        var paymentIntent = (PaymentIntent) stripeObject;
        return Long.valueOf(paymentIntent.getMetadata().get("order_id"));
    }

    private SessionCreateParams.LineItem createLineItem(OrderItemEntity item) {
        return SessionCreateParams.LineItem.builder()
                .setQuantity(Long.valueOf(item.getQuantity()))
                .setPriceData(createPriceData(item))
                .build();
    }

    private static SessionCreateParams.LineItem.PriceData createPriceData(OrderItemEntity item) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmountDecimal(item.getUnitPrice().multiply(BigDecimal.valueOf(100)))
                .setProductData(createProductData(item))
                .build();
    }

    private static SessionCreateParams.LineItem.PriceData.ProductData createProductData(OrderItemEntity item) {
        return SessionCreateParams.LineItem.PriceData.ProductData.builder()
                .setName(item.getProduct().getName())
                .build();
    }
}
