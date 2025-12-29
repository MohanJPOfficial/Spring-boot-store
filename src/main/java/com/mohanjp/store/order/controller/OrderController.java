package com.mohanjp.store.order.controller;

import com.mohanjp.store.dto.ErrorDto;
import com.mohanjp.store.order.dto.OrderDto;
import com.mohanjp.store.order.exception.OrderNotFoundException;
import com.mohanjp.store.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.security.AccessControlException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrderById(@PathVariable("orderId") Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Void> handleOrderNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AccessControlException.class)
    public ResponseEntity<ErrorDto> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorDto(ex.getMessage()));
    }
}
