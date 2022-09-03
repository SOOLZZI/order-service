package com.haruhanjan.orderservice.dto;

import com.haruhanjan.orderservice.entity.Alcohol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AlcoholResponse {
    private String name;
    private String seller;
    private Integer price;

    public Alcohol toEntity() {
        return new Alcohol().builder()
                .name(name)
                .seller(seller)
                .price(price)
                .build();
    }
}
