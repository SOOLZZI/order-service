package com.haruhanjan.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    @Getter
    private Long id;
    private String accountId;
    private String nickname;
    private String role;
}