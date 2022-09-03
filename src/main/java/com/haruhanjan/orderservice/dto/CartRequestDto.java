package com.haruhanjan.orderservice.dto;

import lombok.Setter;

@Setter
public class CartRequestDto {
    private Long productId;

    private Long userId;

    private Integer quantity;

    private Integer price;
}
