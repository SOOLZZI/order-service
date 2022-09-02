package com.haruhanjan.orderservice.entity;

public enum OrderState {
    // 결제완료, 배송시작, 배송완료, 취소
    COMPLETE_PAYMENT,
    DELIVERY_START,
    DELIVERY_FINISH,
    CANCEL,
}

