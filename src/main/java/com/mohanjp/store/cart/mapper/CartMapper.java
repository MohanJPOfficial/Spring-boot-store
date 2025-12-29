package com.mohanjp.store.cart.mapper;

import com.mohanjp.store.cart.dto.CartDto;
import com.mohanjp.store.cart.dto.CartItemDto;
import com.mohanjp.store.cart.entity.CartEntity;
import com.mohanjp.store.cart.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(CartEntity cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItemEntity cartItem);
}
