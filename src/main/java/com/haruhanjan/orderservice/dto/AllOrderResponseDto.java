package com.haruhanjan.orderservice.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AllOrderResponseDto {
    private String state;
    private LocalDateTime orderDate;
    private Integer totalPrice;


    private List<String> alcoholNameList;

    public AllOrderResponseDto(String state, LocalDateTime orderDate, Integer totalPrice) {
        this.state = state;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public void setAlcoholNameList(List<String> alcoholNameList) {
        this.alcoholNameList = alcoholNameList;
    }
}
