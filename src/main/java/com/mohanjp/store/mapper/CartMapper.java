package com.mohanjp.store.mapper;

import com.mohanjp.store.dto.CartDto;
import com.mohanjp.store.dto.CartItemDto;
import com.mohanjp.store.entity.CartEntity;
import com.mohanjp.store.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(CartEntity cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItemEntity cartItem);
}
