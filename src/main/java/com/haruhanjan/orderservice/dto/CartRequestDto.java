package com.haruhanjan.orderservice.dto;

import com.haruhanjan.orderservice.entity.Alcohol;
import com.haruhanjan.orderservice.entity.Cart;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
public class CartRequestDto {
    private List<CreateOrderItemRequestDto> cartItemList; // alcoholId, quantity

    public List<Cart> toEntity() {
        return Cart.builder()
                .alcohol(alcohol)
                .userId(userId)
                .quantity(quantity)
                .build();
    }
}
