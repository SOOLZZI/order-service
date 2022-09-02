package com.haruhanjan.orderservice.controller;

import com.haruhanjan.orderservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final UserService userService;

    @GetMapping("/api/test")
    public Long getUser() {
        return userService.getUserId();
    }
}
