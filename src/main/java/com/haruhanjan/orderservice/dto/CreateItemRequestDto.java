package com.haruhanjan.orderservice.dto;

import com.haruhanjan.orderservice.entity.Alcohol;
import com.haruhanjan.orderservice.entity.Order;
import com.haruhanjan.orderservice.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
public class CreateItemRequestDto {
    @Getter
    @NotNull
    private Long alcoholId;
    @Min(0)
    private Integer quantity;

    public OrderItem toEntity(Alcohol alcohol, Order order) {
        return OrderItem.builder()
                .alcohol(alcohol)
                .order(order)
                .quantity(quantity)
                .build();
    }

    public int getQuantity(){
        return this.quantity;
    }
}
