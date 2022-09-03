package com.haruhanjan.orderservice.entity;

import lombok.*;

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
    private OrderState state = OrderState.COMPLETE_PAYMENT;

    private LocalDateTime orderDate;

    // 최종 결제 금액? 아님 orderItem 계산한 총 금액?
    private Integer totalPrice;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    public Order(Long userId, LocalDateTime orderDate) {
        this.userId = userId;
        this.orderDate = orderDate;
    }

}
