package com.mohanjp.store.payment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CheckoutRequest {

    @NotNull(message = "Cart ID cannot be null")
    private UUID cartId;
}
