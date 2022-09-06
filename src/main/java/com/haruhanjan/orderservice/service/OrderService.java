package com.haruhanjan.orderservice.service;

import com.haruhanjan.orderservice.dto.*;
import com.haruhanjan.orderservice.entity.Alcohol;
import com.haruhanjan.orderservice.entity.Order;
import com.haruhanjan.orderservice.repository.OrderItemRepository;
import com.haruhanjan.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final InternalWebService internalWebService;

    public List<AllOrderResponseDto> get(Long userId) {
        List<Order> orderList = orderRepository.findByUserId(userId).orElse(null);
        if (orderList==null)
            return null;

        return orderList.stream()
                .map(i -> {
                    // TODO
                    AllOrderResponseDto dto = new AllOrderResponseDto(i.getState().name(), i.getOrderDate(), i.getTotalPrice());
                    dto.setAlcoholNameList(i.getOrderItems()
                            .stream()
                            .map(j -> j.getAlcohol()
                                    .getName())
                            .collect(Collectors.toList())
                    );
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderResponseDto save(Long userId, @Valid CreateOrderRequestDto dto) {
        // order 생성
        log.info("userId: {}", userId);
        log.info("dto.toEntity(): {}",dto.toEntity());

        Order savedOrder = orderRepository.save(dto.toEntity());
        savedOrder.setUser(userId);

        // 개별 order item 저장
        List<OrderItemResponseDto> orderItemList = dto.getOrderItemList().stream()
                .map(item -> {
                    Alcohol alcohol = internalWebService.callApiGetAlcoholById(item.getAlcoholId());

                    orderItemRepository.save(item.toEntity(alcohol, savedOrder));

                    OrderItemResponseDto responseDto = OrderItemResponseDto.builder()
                            .quantity(item.getQuantity())
                            .alcoholId(item.getAlcoholId())
                            .build();
                    responseDto.setPrice(item.getQuantity()* alcohol.getPrice());

                    return responseDto;
                }).collect(Collectors.toList());

        // 전체 구매 금액 계산
        int total = 0;

        for(OrderItemResponseDto orderItem: orderItemList){
            log.info("getPrice: {}",orderItem.getPrice());
            total += orderItem.getPrice();
        }
        log.info("total price: {}",total);
        savedOrder.setTotalPrice(total);

        return makeOrderResponse(savedOrder, orderItemList);
    }

    private OrderResponseDto makeOrderResponse(Order order, List<OrderItemResponseDto> orderItemList) {
        return OrderResponseDto.builder()
                .orderDate(order.getOrderDate())
                .state(order.getState().name())
                .totalPrice(order.getTotalPrice())
                .orderItemList(orderItemList)
                .build();
    }

    public OrderResponseDto getOne(Long userId, Long id) {
        Order order = orderRepository.findByIdAndUserId(id,userId).orElseThrow(EntityNotFoundException::new);

        List<OrderItemResponseDto> orderItemList = order.getOrderItems().stream()
                .map(i -> {
                    OrderItemResponseDto dto = new OrderItemResponseDto(i.getAlcohol().getId(), i.getQuantity());
                    dto.setPrice(i.getQuantity() * i.getAlcohol().getPrice());
                    return dto;
                })
                .collect(Collectors.toList());

        return OrderResponseDto.builder()
                .orderItemList(orderItemList)
                .state(order.getState().name())
                .orderDate(order.getOrderDate())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    @Transactional
    public void patchState(Long userId, Long orderId, PatchOrderStateDto dto) {
        log.info("userId: {}", userId);
        log.info("orderId: {}", orderId);

        Order target = orderRepository.findByIdAndUserId(orderId,userId).orElseThrow(EntityNotFoundException::new);
        target.patchState(dto);
    }
}
