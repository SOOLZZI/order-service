package com.haruhanjan.orderservice.controller;

import com.haruhanjan.orderservice.service.InternalWebService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {
    private final InternalWebService internalWebService;

    @GetMapping("/api/test")
    public Long getUser(@CookieValue String access_token) {
        log.info(access_token);
        return internalWebService.getUserId(access_token);
    }

}
