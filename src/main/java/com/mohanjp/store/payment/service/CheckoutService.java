package com.mohanjp.store.payment.service;

import com.mohanjp.store.payment.dto.CheckoutRequest;
import com.mohanjp.store.payment.dto.CheckoutResponse;
import com.mohanjp.store.order.entity.OrderEntity;
import com.mohanjp.store.cart.exception.CartEmptyException;
import com.mohanjp.store.cart.exception.CartNotFoundException;
import com.mohanjp.store.payment.exception.PaymentException;
import com.mohanjp.store.cart.repository.CartRepository;
import com.mohanjp.store.order.repository.OrderRepository;
import com.mohanjp.store.auth.service.AuthService;
import com.mohanjp.store.cart.service.CartService;
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
