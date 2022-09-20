package com.haruhanjan.orderservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ItemResponseDto {
    private Long alcoholId;
    private String alcoholName;
    private Integer quantity;
    private Integer price;

    public ItemResponseDto(Long alcoholId, Integer quantity) {
        this.alcoholId = alcoholId;
        this.quantity = quantity;
    }

    @Builder
    public ItemResponseDto(Long alcoholId, String alcoholName, Integer quantity, Integer price) {
        this.alcoholId = alcoholId;
        this.alcoholName = alcoholName;
        this.quantity = quantity;
        this.price = price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
