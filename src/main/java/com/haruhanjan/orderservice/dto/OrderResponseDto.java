package com.haruhanjan.orderservice.dto;

import com.haruhanjan.orderservice.entity.OrderState;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderResponseDto {
    private Long alcoholId;
    private Long orderItemId;
    private OrderState state;
    private LocalDateTime orderDate;
    private Integer totalPrice;
    private List<OrderItemResponseDto> orderItemList;
}
