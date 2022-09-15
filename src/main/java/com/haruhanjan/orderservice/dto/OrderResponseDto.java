package com.haruhanjan.orderservice.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderResponseDto {
    private String state;
    private LocalDateTime orderDate;
    private Integer totalPrice;
    private List<ItemResponseDto> orderItemList;

    @Builder
    public OrderResponseDto(String state, LocalDateTime orderDate, Integer totalPrice, List<ItemResponseDto> orderItemList) {
        this.state = state;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderItemList = orderItemList;
    }
}
