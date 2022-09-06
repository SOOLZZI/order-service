package com.haruhanjan.orderservice.dto;

import com.haruhanjan.orderservice.entity.Alcohol;
import com.haruhanjan.orderservice.entity.Cart;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
public class CartRequestDto {
    @NotNull
    private Alcohol alcohol;
    @NotNull
    private Long userId;
    @Min(0)
    private Integer quantity;

    public Cart toEntity() {
        return Cart.builder()
                .alcohol(alcohol)
                .userId(userId)
                .quantity(quantity)
                .build();
    }
}
