package com.haruhanjan.orderservice.mapper;

import com.haruhanjan.orderservice.dto.AllOrderResponseDto;
import com.haruhanjan.orderservice.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final OrderItemMapper orderItemMapper;

    public AllOrderResponseDto toResponseDTO(Order order) {
        List<String> orderItems = orderItemMapper.toAlcoholNameList(order.getOrderItems());
        //  orderItem 변환~
        return new AllOrderResponseDto(
                order.getState().name(),
                order.getOrderDate(),
                order.getTotalPrice(),
                orderItems
        );

    }
}
