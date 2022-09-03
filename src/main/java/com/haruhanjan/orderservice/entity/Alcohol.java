package com.haruhanjan.orderservice.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Alcohol {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="alcohol_copy_id")
    private Long id;
    private String name;
    private String seller;
    private Integer price;

    @Builder
    public Alcohol(String seller, Integer price, String name) {
        this.seller = seller;
        this.price = price;
        this.name = name;
    }
}
