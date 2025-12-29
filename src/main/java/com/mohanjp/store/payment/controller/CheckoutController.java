package com.mohanjp.store.payment.controller;

import com.mohanjp.store.payment.dto.CheckoutRequest;
import com.mohanjp.store.payment.dto.CheckoutResponse;
import com.mohanjp.store.dto.ErrorDto;
import com.mohanjp.store.cart.exception.CartEmptyException;
import com.mohanjp.store.cart.exception.CartNotFoundException;
import com.mohanjp.store.payment.exception.PaymentException;
import com.mohanjp.store.repository.OrderRepository;
import com.mohanjp.store.payment.service.CheckoutService;
import com.mohanjp.store.payment.service.WebhookRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;
    private final OrderRepository orderRepository;

    @PostMapping
    public CheckoutResponse checkout(
            @Valid @RequestBody CheckoutRequest request
    ) {
        return checkoutService.checkout(request);
    }

    @PostMapping("/webhook")
    public void handleWebhook(
            @RequestHeader Map<String, String> headers,
            @RequestBody String payload
    ) {
        checkoutService.handleWebhookEvent(new WebhookRequest(headers, payload));
    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<?> handlePaymentException(PaymentException e) {
        System.out.println("PaymentException: " + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto("Error creating a checkout session " + e.getMessage()));
    }

    @ExceptionHandler({CartNotFoundException.class, CartEmptyException.class})
    public ResponseEntity<ErrorDto> handleException(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorDto(e.getMessage()));
    }
}
