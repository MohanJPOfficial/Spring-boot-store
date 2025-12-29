package com.mohanjp.store.order.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private long id;
    private String status;
    private LocalDateTime createdAt;
    private List<OrderItemDto> items;
    private BigDecimal totalPrice;
}
