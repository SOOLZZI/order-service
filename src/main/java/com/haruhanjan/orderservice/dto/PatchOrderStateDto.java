package com.haruhanjan.orderservice.dto;

import com.haruhanjan.orderservice.entity.OrderState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PatchOrderStateDto {
    private OrderState orderState;

    @Builder
    public PatchOrderStateDto(OrderState orderState) {
        this.orderState = orderState;
    }
}
