package com.haruhanjan.orderservice.dto;

import com.haruhanjan.orderservice.entity.Alcohol_copy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AlcoholResponse {
    private String name;
    private String seller;
    private Integer price;

    public Alcohol_copy toEntity() {
        return new Alcohol_copy().builder()
                .name(name)
                .seller(seller)
                .price(price)
                .build();
    }
}
