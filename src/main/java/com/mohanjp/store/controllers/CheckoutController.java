package com.mohanjp.store.controllers;

import com.mohanjp.store.dto.CheckoutRequest;
import com.mohanjp.store.dto.CheckoutResponse;
import com.mohanjp.store.dto.ErrorDto;
import com.mohanjp.store.exception.CartEmptyException;
import com.mohanjp.store.exception.CartNotFoundException;
import com.mohanjp.store.service.CheckoutService;
import com.stripe.exception.StripeException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping
    public CheckoutResponse checkout(
            @Valid @RequestBody CheckoutRequest request
    ) throws StripeException {
        return checkoutService.checkout(request);
    }

    @ExceptionHandler({CartNotFoundException.class, CartEmptyException.class})
    public ResponseEntity<ErrorDto> handleException(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorDto(e.getMessage()));
    }
}
