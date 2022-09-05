package com.haruhanjan.orderservice.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
public class Alcohol {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="alcohol_id")
    private Long id;
    private Long originId;
    private String name;
    private String seller;
    private Integer price;

    @Builder
    public Alcohol(Long originId,String seller, Integer price, String name) {
        this.originId = originId;
        this.seller = seller;
        this.price = price;
        this.name = name;
    }

    public int getPrice(){
        return this.price;
    }
}
