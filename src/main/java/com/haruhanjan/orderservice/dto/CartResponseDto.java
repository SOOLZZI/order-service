package com.haruhanjan.orderservice.dto;

import com.haruhanjan.orderservice.entity.Alcohol;
import lombok.Builder;

import java.util.List;

public class CartResponseDto {

    private List<CartItemResponseDto> items;
    private Integer totalPrice;

    @Builder
    public CartResponseDto(List<CartItemResponseDto> items, Integer totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }
}