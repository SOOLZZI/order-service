package com.haruhanjan.orderservice.mapper;

import com.haruhanjan.orderservice.dto.CartItemResponseDto;
import com.haruhanjan.orderservice.dto.CartResponseDto;
import com.haruhanjan.orderservice.dto.CreateItemRequestDto;
import com.haruhanjan.orderservice.dto.ItemResponseDto;
import com.haruhanjan.orderservice.entity.Alcohol;
import com.haruhanjan.orderservice.entity.Cart;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    public Cart toEntity(CreateItemRequestDto dto, Alcohol alcohol, Long userId) {
        return Cart.builder()
                .alcohol(alcohol)
                .quantity(dto.getQuantity())
                .userId(userId)
                .build();
    }

    public CartItemResponseDto toCartResponseDto(Cart cart) {
        return  CartItemResponseDto.builder()
                .cartId(cart.getId())
                .alcoholId(cart.getAlcohol().getId())
                .alcoholName(cart.getAlcohol().getName())
                .quantity(cart.getQuantity())
                .price(cart.getAlcohol().getPrice() * cart.getQuantity())
                .build();
    }
}
