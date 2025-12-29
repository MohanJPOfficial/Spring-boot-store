package com.mohanjp.store.order.mapper;

import com.mohanjp.store.order.dto.OrderDto;
import com.mohanjp.store.order.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(OrderEntity order);
}
