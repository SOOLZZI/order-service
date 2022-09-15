package com.haruhanjan.orderservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemResponseDto {
    private Long alcoholId;
    private Integer quantity;
    private Integer price;

    public ItemResponseDto(Long alcoholId, Integer quantity) {
        this.alcoholId = alcoholId;
        this.quantity = quantity;
    }

    @Builder
    public ItemResponseDto(Long alcoholId, Integer quantity, Integer price) {
        this.alcoholId = alcoholId;
        this.quantity = quantity;
        this.price = price;
    }

    public void setPrice(int price){
        this.price = price;
    }
}