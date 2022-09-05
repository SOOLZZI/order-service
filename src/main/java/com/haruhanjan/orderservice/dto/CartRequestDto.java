package com.haruhanjan.orderservice.dto;

import com.haruhanjan.orderservice.entity.Alcohol;
import com.haruhanjan.orderservice.entity.Cart;
import com.haruhanjan.orderservice.entity.Order;
import com.haruhanjan.orderservice.entity.OrderItem;
import lombok.Setter;

@Setter
public class CartRequestDto {
    private Alcohol alcohol;
    private Long userId;
    private Integer quantity;

    public Cart toEntity() {
        return Cart.builder()
                .alcohol(alcohol)
                .userId(userId)
                .quantity(quantity)
                .build();
    }
}
