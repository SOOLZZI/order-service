package com.haruhanjan.orderservice.dto;

import com.haruhanjan.orderservice.entity.Order;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Validated(CreateOrderItemRequestDto.class)
public class CreateOrderRequestDto {
    @Getter
    private List<CreateOrderItemRequestDto> orderItemList; // alcoholId, quantity
    private LocalDateTime orderDate;

    public Order toEntity(){
        return Order.builder()
                .orderDate(orderDate)
                .build();
    }
}
