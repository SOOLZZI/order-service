package com.haruhanjan.orderservice.dto;

import com.haruhanjan.orderservice.entity.OrderState;
import lombok.Setter;

@Setter
public class PatchOrderStateDto {
    private OrderState orderState;
}
