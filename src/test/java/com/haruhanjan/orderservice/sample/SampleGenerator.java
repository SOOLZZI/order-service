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

    public String createOrderRequestJson(){
        return "{\n" +
                "    \"orderItemList\":[\n" +
                "        {\n" +
                "            \"alcoholId\": 1,\n" +
                "            \"quantity\": 3\n" +
                "        },\n" +
                "        {\n" +
                "            \"alcoholId\": 2,\n" +
                "            \"quantity\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"alcoholId\": 3,\n" +
                "            \"quantity\": 10\n" +
                "        },\n" +
                "        {\n" +
                "            \"alcoholId\": 1,\n" +
                "            \"quantity\": 10900\n" +
                "        }\n" +
                "    ],\n" +
                "    \"orderDate\": \"2022-09-05T22:05:14\"\n" +
                "}";
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

    public String patchOrderStateJson() {
        return "{\n" +
                "  \"orderState\":\"CANCEL\"\n" +
                "}";
    }

    public <T> String toJson(T data) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(data);
    }
}
