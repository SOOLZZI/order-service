package com.haruhanjan.orderservice.dto;

import com.haruhanjan.orderservice.entity.Alcohol;
import lombok.Builder;

public class CartResponseDto {
    private Alcohol alcohol;
    private Long userId;
    private Integer quantity;

    @Builder
    public CartResponseDto(Alcohol alcohol, Long userId, Integer quantity) {
        this.alcohol = alcohol;
        this.userId = userId;
        this.quantity = quantity;
    }
}
