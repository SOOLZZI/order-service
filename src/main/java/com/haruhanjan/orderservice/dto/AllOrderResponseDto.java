package com.haruhanjan.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class AllOrderResponseDto {

    private String state;
    private LocalDateTime orderDate;
    private Integer totalPrice;



    private List<String> alcoholNameList;

//    public AllOrderResponseDto(String state, LocalDateTime orderDate, Integer totalPrice) {
//        this.state = state;
//        this.orderDate = orderDate;
//        this.totalPrice = totalPrice;
//    }
//
//    public AllOrderResponseDto(Order entity) {
//        this.state = entity.getState().name();
//        this.orderDate = entity.getOrderDate();
//        this.totalPrice = entity.getTotalPrice();
//        this.alcoholNameList = entity.getOrderItems()
//                .stream()
//                .map()
//
//                .collect(Collectors.toList());
//    }
//
//    public void setAlcoholNameList(List<String> alcoholNameList) {
//        this.alcoholNameList = alcoholNameList;
//    }
}