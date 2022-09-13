package com.haruhanjan.orderservice.mapper;

import com.haruhanjan.orderservice.dto.OrderItemResponseDto;
import com.haruhanjan.orderservice.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderItemMapper {

    public String toAlcoholName(OrderItem orderItem) {
        return orderItem.getAlcohol().getName();
    }

    public List<String> toAlcoholNameList(List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(this::toAlcoholName)
                .collect(Collectors.toList());
    }

    public OrderItemResponseDto toOrderItemResponseDto(OrderItem orderItem) {
        return OrderItemResponseDto.builder()
                .quantity(orderItem.getQuantity())
                .alcoholId(orderItem.getAlcohol().getId())
                .price(orderItem.getAlcohol().getPrice() * orderItem.getQuantity())
                .build();
    }
}
