package com.mohanjp.store.mapper;

import com.mohanjp.store.dto.order.OrderDto;
import com.mohanjp.store.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(OrderEntity order);
}
