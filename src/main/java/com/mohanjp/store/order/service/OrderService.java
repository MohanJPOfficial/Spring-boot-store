package com.mohanjp.store.order.service;

import com.mohanjp.store.auth.service.AuthService;
import com.mohanjp.store.order.dto.OrderDto;
import com.mohanjp.store.order.exception.OrderNotFoundException;
import com.mohanjp.store.order.mapper.OrderMapper;
import com.mohanjp.store.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getAllOrders() {
        var user = authService.getCurrentUser();
        var orders = orderRepository.getOrdersByCustomer(user);
        return orders.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    public OrderDto getOrderById(long orderId) {
        var order = orderRepository
                .getOrderWithItems(orderId)
                .orElseThrow(OrderNotFoundException::new);

        var user = authService.getCurrentUser();
        if (!order.isPlacedBy(user)) {
            throw new AccessDeniedException("You don't have access to this order");
        }

        return orderMapper.toDto(order);
    }
}
