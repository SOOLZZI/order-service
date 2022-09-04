package com.haruhanjan.orderservice.entity;

import com.haruhanjan.orderservice.dto.PatchOrderStateDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Getter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "orders_id")
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

    public void setUser(Long id) {
        this.userId = id;
    }

    public void setTotalPrice(int price) {
        this.totalPrice = price;
    }

    public void patchState(PatchOrderStateDto dto) {
        Optional.ofNullable(dto.getOrderState()).ifPresent(s -> this.state = s);
    }
}
