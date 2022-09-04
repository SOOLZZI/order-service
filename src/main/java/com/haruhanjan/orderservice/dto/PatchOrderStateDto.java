package com.haruhanjan.orderservice.dto;

import com.haruhanjan.orderservice.entity.OrderState;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatchOrderStateDto {
    private OrderState orderState;
}
