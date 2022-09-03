package com.haruhanjan.orderservice.dto;

import com.haruhanjan.orderservice.entity.Alcohol;
import com.haruhanjan.orderservice.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Setter
public class CreateOrderItemRequestDto {
    @Getter
    private Long alcoholId;
    private Integer quantity;


    public CreateOrderItemRequestDto(Long alcoholId, Integer quantity) {
        this.alcoholId = alcoholId;
        this.quantity = quantity;
    }

    public OrderItem toEntity() {
        return OrderItem.builder()
                .quantity(quantity)
                .build();
    }
}
