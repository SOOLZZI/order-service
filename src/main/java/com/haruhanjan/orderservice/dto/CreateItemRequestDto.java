package com.haruhanjan.orderservice.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haruhanjan.orderservice.entity.Alcohol;
import com.haruhanjan.orderservice.entity.Order;
import com.haruhanjan.orderservice.entity.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@NoArgsConstructor
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

    @Builder
    public CreateItemRequestDto(Long alcoholId, Integer quantity) {
        this.alcoholId = alcoholId;
        this.quantity = quantity;
    }
}
