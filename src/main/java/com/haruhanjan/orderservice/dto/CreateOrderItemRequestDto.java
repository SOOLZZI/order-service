package com.haruhanjan.orderservice.dto;

import com.haruhanjan.orderservice.entity.Alcohol;
import com.haruhanjan.orderservice.entity.Order;
import com.haruhanjan.orderservice.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateOrderItemRequestDto {
    private Long alcoholId;
    private Integer quantity;

    public OrderItem toEntity(Alcohol alcohol, Order order) {
        return OrderItem.builder()
                .alcohol(alcohol)
                .order(order)
                .quantity(quantity)
                .build();
    }
}
