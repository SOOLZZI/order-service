package com.haruhanjan.orderservice.service;

import com.haruhanjan.orderservice.dto.*;
import com.haruhanjan.orderservice.entity.Alcohol;
import com.haruhanjan.orderservice.entity.Order;
import com.haruhanjan.orderservice.mapper.OrderItemMapper;
import com.haruhanjan.orderservice.mapper.OrderMapper;
import com.haruhanjan.orderservice.repository.OrderItemRepository;
import com.haruhanjan.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class OrderService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final AlcoholService alcoholService;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    public List<SlimOrderResponseDto> getAll(Long userId, Pageable pageable) {
        Page<Order> orderList = orderRepository.findAllByUserId(userId, pageable);
        return orderList.stream()
                .map(orderMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderResponseDto save(Long userId, @Valid CreateOrderRequestDto dto) {
        // order 생성
        Order savedOrder = orderRepository.save(dto.toEntity());
        savedOrder.setUser(userId);

        // 개별 order item 저장
        // List<OrderItem> 필요
        //Alcohol 필요
        List<ItemResponseDto> orderItemList = dto.getOrderItemList().stream()
                .map(item -> {
                    Alcohol alcohol = alcoholService.findById(item.getAlcoholId());
                    return item.toEntity(alcohol, savedOrder);
                })
                .map(orderItemRepository::save)
                .map(orderItemMapper::toOrderItemResponseDto)
                .collect(Collectors.toList());

        // 전체 구매 금액 계산
        int total = 0;

        for (ItemResponseDto orderItem : orderItemList) {
            log.info("getPrice: {}", orderItem.getPrice());
            total += orderItem.getPrice();
        }

        savedOrder.setTotalPrice(total);

        return orderMapper.toOrderResponseDto(savedOrder, orderItemList);
    }


    public OrderResponseDto getOne(Long userId, Long orderId) {
        Order order = orderRepository.findByIdAndUserId(orderId, userId).orElseThrow(EntityNotFoundException::new);

        List<ItemResponseDto> orderItemList = order.getOrderItems().stream()
                .map(orderItemMapper::toOrderItemResponseDto)
                .collect(Collectors.toList());

        return orderMapper.toOrderResponseDto(order, orderItemList);
    }
    // rest

    @Transactional
    public void changeState(Long userId, Long orderId, PatchOrderStateDto dto) {
        Order target = orderRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(EntityNotFoundException::new);
        target.patchState(dto);
    }
}
