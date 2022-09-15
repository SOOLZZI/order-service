package com.haruhanjan.orderservice.mapper;

import com.haruhanjan.orderservice.dto.ItemResponseDto;
import com.haruhanjan.orderservice.dto.OrderResponseDto;
import com.haruhanjan.orderservice.dto.SlimOrderResponseDto;
import com.haruhanjan.orderservice.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final OrderItemMapper orderItemMapper;

    public SlimOrderResponseDto toResponseDTO(Order order) {
        List<String> orderItems = orderItemMapper.toAlcoholNameList(order.getOrderItems());
        //  orderItem 변환~
        return new SlimOrderResponseDto(
                order.getState().name(),
                order.getOrderDate(),
                order.getTotalPrice(),
                orderItems
        );
    }

    public OrderResponseDto toOrderResponseDto(Order order, List<ItemResponseDto> orderItemList) {
        return OrderResponseDto.builder()
                .orderDate(order.getOrderDate())
                .state(order.getState().name())
                .totalPrice(order.getTotalPrice())
                .orderItemList(orderItemList)
                .build();
    }
}
