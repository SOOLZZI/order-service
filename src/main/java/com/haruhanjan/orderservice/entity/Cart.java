package com.haruhanjan.orderservice.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
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

    @Builder
    public Cart(Alcohol alcohol, Long userId, Integer quantity) {
        this.alcohol = alcohol;
        this.userId = userId;
        this.quantity = quantity;
    }
}
