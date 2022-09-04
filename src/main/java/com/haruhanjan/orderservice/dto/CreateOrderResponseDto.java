package com.haruhanjan.orderservice.dto;

import com.haruhanjan.orderservice.entity.OrderState;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CreateOrderResponseDto {
    private String state;
    private LocalDateTime orderDate;
    private Integer totalPrice;
    private List<OrderItemResponseDto> orderItemList;

    @Builder
    public CreateOrderResponseDto(String state, LocalDateTime orderDate, Integer totalPrice, List<OrderItemResponseDto> orderItemList) {
        this.state = state;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderItemList = orderItemList;
    }
}
