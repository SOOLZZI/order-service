package com.haruhanjan.orderservice.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id @GeneratedValue
    @Column(name="cart_id")
    private Long id;
    // 수량, 가격? 품절유무->재고테이블로 관리... 근데 재고 안할거임, 품절됐다면 나중에 판매자가 취소시켜

    private Long productId;

    private Long userId;

    private Integer quantity;

    private Integer price;
}
