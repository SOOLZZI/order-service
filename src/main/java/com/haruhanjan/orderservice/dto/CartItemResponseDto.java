package com.haruhanjan.orderservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CartItemResponseDto extends ItemResponseDto {
    private Long cartId;

    @Builder
    public CartItemResponseDto(Long alcoholId, Integer quantity, Long cartId) {
        super(alcoholId, quantity);
        this.cartId = cartId;
    }

    @Builder
    public CartItemResponseDto(Long alcoholId, String alcoholName, Integer quantity, Integer price, Long cartId) {
        super(alcoholId, alcoholName, quantity, price);
        this.cartId = cartId;
    }
}
