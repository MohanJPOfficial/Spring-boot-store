package com.mohanjp.store.service;

import com.mohanjp.store.dto.order.OrderDto;
import com.mohanjp.store.mapper.OrderMapper;
import com.mohanjp.store.repository.OrderRepository;
import lombok.AllArgsConstructor;
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
        var orders = orderRepository.getAllByCustomer(user);
        return orders.stream()
                .map(orderMapper::toDto)
                .toList();
    }
}
