package com.mohanjp.store.service;

import com.mohanjp.store.dto.CheckoutRequest;
import com.mohanjp.store.dto.CheckoutResponse;
import com.mohanjp.store.entity.OrderEntity;
import com.mohanjp.store.exception.CartEmptyException;
import com.mohanjp.store.exception.CartNotFoundException;
import com.mohanjp.store.exception.PaymentException;
import com.mohanjp.store.repository.CartRepository;
import com.mohanjp.store.repository.OrderRepository;
import com.mohanjp.store.service.paymentGateway.PaymentGateway;
import com.mohanjp.store.service.paymentGateway.WebhookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final AuthService authService;
    private final CartService cartService;
    private final PaymentGateway paymentGateway;

    @Transactional
    public CheckoutResponse checkout(CheckoutRequest checkoutRequest) {
        var cart = cartRepository.getCartWithItems(checkoutRequest.getCartId()).orElse(null);

        if (cart == null) {
            throw new CartNotFoundException();
        }

        if (cart.isEmpty()) {
            throw new CartEmptyException();
        }

        var order = OrderEntity.fromCart(cart, authService.getCurrentUser());

        orderRepository.save(order);

        try {
            // Create a checkout session
            var session = paymentGateway.createCheckoutSession(order);
            cartService.clearCart(cart.getId());

            return new CheckoutResponse(order.getId(), session.getCheckoutUrl());

        } catch (PaymentException ex) {
            orderRepository.delete(order);
            throw ex;
        }
    }

    public void handleWebhookEvent(WebhookRequest webhookRequest) {
        paymentGateway
                .parseWebhookRequest(webhookRequest)
                .ifPresent(paymentResult -> {
                    var order = orderRepository.findById(paymentResult.getOrderId()).orElseThrow();
                    order.setStatus(paymentResult.getPaymentStatus());
                    orderRepository.save(order);
                });
    }
}
