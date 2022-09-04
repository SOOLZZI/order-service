package com.haruhanjan.orderservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderItemResponseDto {
    private Long alcoholId;
    private Integer quantity;
    private Integer price;

    @Builder
    public OrderItemResponseDto(Long alcoholId, Integer quantity) {
        this.alcoholId = alcoholId;
        this.quantity = quantity;
    }

    public void setPrice(int price){
        this.price = price;
    }
}
