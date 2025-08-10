package com.mohanjp.store.service;

import com.mohanjp.store.dto.CheckoutRequest;
import com.mohanjp.store.dto.CheckoutResponse;
import com.mohanjp.store.entity.OrderEntity;
import com.mohanjp.store.exception.CartEmptyException;
import com.mohanjp.store.exception.CartNotFoundException;
import com.mohanjp.store.repository.CartRepository;
import com.mohanjp.store.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckoutService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final AuthService authService;
    private final CartService cartService;

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
        cartService.clearCart(cart.getId());

        return new CheckoutResponse(order.getId());
    }
}
