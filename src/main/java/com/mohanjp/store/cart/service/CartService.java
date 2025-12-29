package com.mohanjp.store.cart.service;

import com.mohanjp.store.cart.dto.CartDto;
import com.mohanjp.store.cart.dto.CartItemDto;
import com.mohanjp.store.cart.entity.CartEntity;
import com.mohanjp.store.cart.exception.CartNotFoundException;
import com.mohanjp.store.product.exception.ProductNotFoundException;
import com.mohanjp.store.cart.mapper.CartMapper;
import com.mohanjp.store.cart.repository.CartRepository;
import com.mohanjp.store.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    public CartDto createCart() {

        var cart = new CartEntity();
        cartRepository.save(cart);

        return cartMapper.toDto(cart);
    }

    public CartItemDto addToCart(UUID carId, Long productId) {
        var cart = cartRepository.getCartWithItems(carId).orElse(null);

        if (cart == null) {
            throw new CartNotFoundException();
        }

        var product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            throw new ProductNotFoundException();
        }

        var cartItem = cart.addItem(product);
        cartRepository.save(cart);

        return cartMapper.toDto(cartItem);
    }

    public CartDto getCart(UUID cartId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);

        if (cart == null) {
            throw new CartNotFoundException();
        }

        return cartMapper.toDto(cart);
    }

    public CartItemDto updateCartItem(
            UUID cartId,
            Long productId,
            Integer quantity
    ) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);

        if (cart == null) {
            throw new CartNotFoundException();
        }

        var cartItem = cart.getItem(productId);

        if (cartItem == null) {
            throw new ProductNotFoundException();
        }

        cartItem.setQuantity(quantity);
        cartRepository.save(cart);

        return cartMapper.toDto(cartItem);
    }

    public void removeItemFromCart(
            UUID cartId,
            Long productId
    ) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);

        if (cart == null) {
            throw new CartNotFoundException();
        }

        cart.removeItem(productId);

        cartRepository.save(cart);
    }

    public void clearCart(UUID cartId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);

        if (cart == null) {
            throw new CartNotFoundException();
        }

        cart.clear();
        cartRepository.save(cart);
    }
}
