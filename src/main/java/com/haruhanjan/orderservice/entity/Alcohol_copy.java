package com.haruhanjan.orderservice.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Alcohol_copy {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="alcohol_copy_id")
    private Long id;
    private String name;
    private String seller;
    private Integer price;

    @Builder
    public Alcohol_copy(String seller, Integer price, String name) {
        this.seller = seller;
        this.price = price;
        this.name = name;
    }
}
