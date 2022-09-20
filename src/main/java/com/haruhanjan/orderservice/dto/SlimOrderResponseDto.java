package com.haruhanjan.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class SlimOrderResponseDto {

    private String state;
    private LocalDateTime orderDate;
    private Integer totalPrice;

    private List<String> alcoholNameList;

    @Builder
    public SlimOrderResponseDto(String state, LocalDateTime orderDate, Integer totalPrice, List<String> alcoholNameList) {
        this.state = state;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.alcoholNameList = alcoholNameList;
    }
}