package com.mohanjp.store.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderProductDto {
    private long id;
    private String name;
    private BigDecimal price;
}
