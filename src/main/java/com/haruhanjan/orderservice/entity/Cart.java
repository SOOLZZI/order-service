package com.haruhanjan.orderservice.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id @GeneratedValue
    @Column(name="cart_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alcohol_id")
    private Alcohol alcohol;

    private Long userId;

    private Integer quantity;
}
