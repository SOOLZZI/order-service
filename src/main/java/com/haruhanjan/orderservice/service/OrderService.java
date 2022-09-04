package com.haruhanjan.orderservice.service;

import com.haruhanjan.orderservice.dto.*;
import com.haruhanjan.orderservice.entity.Alcohol;
import com.haruhanjan.orderservice.entity.Order;
import com.haruhanjan.orderservice.entity.OrderItem;
import com.haruhanjan.orderservice.repository.AlcoholRepository;
import com.haruhanjan.orderservice.repository.OrderItemRepository;
import com.haruhanjan.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final AlcoholRepository alcoholRepository;
    private final InternalWebService internalWebService;

    public List<CreateOrderResponseDto> get(Long userId) {
        List<Order> orderList = orderRepository.findByUserId(userId).orElse(null);
        if (orderList==null)
            return null;

        // 내일 할래...
        return orderList.stream()
                .map()
                .collect(Collectors.toList());
    }

    public CreateOrderResponseDto save(Long userId, CreateOrderRequestDto dto) {
        // order 생성
        Order savedOrder = orderRepository.save(dto.toEntity());
        savedOrder.setUser(userId);

        // 개별 order item 저장
        List<OrderItemResponseDto> orderItemList = dto.getOrderItemList().stream()
                .map(item -> {
                    AlcoholResponse ar = internalWebService.getAlcoholById(item.getAlcoholId()).;
                    Alcohol alcohol = alcoholRepository.findByOriginId(ar.getId()).orElseThrow(EntityNotFoundException::new),

                    orderItemRepository.save(item.toEntity(alcohol, savedOrder));

                    return OrderItemResponseDto.builder()
                            .quantity(item.getQuantity())
                            .alcoholId(item.getAlcoholId())
                            .build()
                            .setPrice(ar.getPrice() * item.getQuantity());
                }).collect(Collectors.toList());

        // 전체 구매 금액 계산
        AtomicInteger totalPrice = new AtomicInteger();
        orderItemList.forEach(i -> totalPrice.addAndGet(i.getPrice()));
        savedOrder.setTotalPrice(totalPrice.get());

        return makeOrderResponse(savedOrder, orderItemList);
    }

    private CreateOrderResponseDto makeOrderResponse(Order order, List<OrderItemResponseDto> orderItemList) {
        return CreateOrderResponseDto.builder()
                .orderDate(order.getOrderDate())
                .state(order.getState().name())
                .totalPrice(order.getTotalPrice())
                .orderItemList(orderItemList)
                .build();
    }

    public CreateOrderResponseDto getOne(Long userId, Long id) {
    }

    public void patchState(Long userId, Long orderId, PatchOrderStateDto dto) {
        Order target = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        target.patchState(dto);
    }
}
