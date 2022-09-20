package com.haruhanjan.orderservice.sample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haruhanjan.orderservice.dto.*;
import com.haruhanjan.orderservice.entity.OrderState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SampleGenerator {
    private Random rd = new Random();

    public SlimOrderResponseDto slimOrderRequestDto(){
        return SlimOrderResponseDto.builder()
                .state(OrderState.DELIVERY_START.name())
                .alcoholNameList(alcoholNameList())
                .totalPrice(rd.nextInt(10)*1000)
                .orderDate(LocalDateTime.now())
                .build();
    }

    private List<String> alcoholNameList() {
        return Arrays.asList("술이름","맥주","소주","와인");
    }

    public CreateItemRequestDto createItemRequestDto() {
        return CreateItemRequestDto.builder()
                .alcoholId(rd.nextLong())
                .quantity(rd.nextInt(5))
                .build();
    }

    public CreateOrderRequestDto createOrderRequestDto() {
        List<CreateItemRequestDto> itemList = new ArrayList<>();
        itemList.add(createItemRequestDto());
        itemList.add(createItemRequestDto());
        itemList.add(createItemRequestDto());
        itemList.add(createItemRequestDto());
        itemList.add(createItemRequestDto());

        return CreateOrderRequestDto.builder()
                .orderDate(LocalDateTime.now())
                .orderItemList(itemList)
                .build();
    }

    public OrderResponseDto orderResponseDto(){
        List<ItemResponseDto> list = itemResponseDtoList();
        return OrderResponseDto.builder()
                .state(OrderState.DELIVERY_START.name())
                .orderDate(LocalDateTime.now())
                .totalPrice(12345)
                .orderItemList(list)
                .build();
    }

    private List<ItemResponseDto> itemResponseDtoList(){
         List<ItemResponseDto> list = new ArrayList<>();
         list.add(itemResponseDto());
         list.add(itemResponseDto());
         list.add(itemResponseDto());
         return list;
    }
    private ItemResponseDto itemResponseDto() {
        return ItemResponseDto.builder()
                .alcoholId(rd.nextLong())
                .alcoholName("술이름")
                .price(rd.nextInt(5)*1000)
                .quantity(rd.nextInt(5))
                .build();
    }

    public PatchOrderStateDto patchOrderStateDto() {
        return PatchOrderStateDto.builder()
                .orderState(OrderState.CANCEL)
                .build();
    }

    public <T> String toJson(T data) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(data);
    }
}
