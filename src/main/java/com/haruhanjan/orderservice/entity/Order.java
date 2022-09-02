package com.haruhanjan.orderservice.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="orders")
public class Order {
    @Id @GeneratedValue
    @Column(name="orders_id")
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    private LocalDateTime orderDate;

    private Integer totalPrice;


    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

}
