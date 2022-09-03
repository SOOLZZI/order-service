package com.haruhanjan.orderservice.dto;

import com.haruhanjan.orderservice.entity.Order;
import com.haruhanjan.orderservice.entity.OrderState;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
public class CreateOrderRequestDto {
    @Getter
    private List<CreateOrderItemRequestDto> orderItemList; // alcoholId, quantity
    private LocalDateTime orderDate;

    public Order toEntity(Long id){
        return Order.builder()
                .userId(id)
                .orderDate(orderDate)
                .build();
    }
}
