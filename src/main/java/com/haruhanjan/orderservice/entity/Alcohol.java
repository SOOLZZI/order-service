package com.haruhanjan.orderservice.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alcohol {
    @Id
    @Column(name="alcohol_id")
    private Long id;

    private String name;
    private String seller;

    private Integer price;

    @Builder
    public Alcohol(Long id,String seller, Integer price, String name) {
        this.id = id;
        this.seller = seller;
        this.price = price;
        this.name = name;
    }

}
