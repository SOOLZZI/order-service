package com.haruhanjan.orderservice.dto;

import com.haruhanjan.orderservice.entity.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;


/*
{
    "orderItemList":[
        {
            "alcoholId": 1,
            "quantity": 3
        },
        {
            "alcoholId": 2,
            "quantity": 1
        },
        {
            "alcoholId": 3,
            "quantity": 10
        },
        {
            "alcoholId": 1,
            "quantity": 10900
        }
    ],
    "orderDate": "2022-09-05T22:05:14"
}
 */

@Setter
@Validated(CreateItemRequestDto.class)
public class CreateOrderRequestDto {
    @Getter
    private List<CreateItemRequestDto> orderItemList; // alcoholId, quantity
    private LocalDateTime orderDate;

    public Order toEntity(){
        return Order.builder()
                .orderDate(orderDate)
                .build();
    }

    @Builder
    public CreateOrderRequestDto(List<CreateItemRequestDto> orderItemList, LocalDateTime orderDate) {
        this.orderItemList = orderItemList;
        this.orderDate = orderDate;
    }
}
