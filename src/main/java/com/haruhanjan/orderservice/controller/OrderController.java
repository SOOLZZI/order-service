package com.haruhanjan.orderservice.controller;

import com.haruhanjan.orderservice.dto.*;
import com.haruhanjan.orderservice.service.InternalWebService;
import com.haruhanjan.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final InternalWebService internalWebService;

    @GetMapping
    public ResponseEntity<List<SlimOrderResponseDto>> getAll(@CookieValue String access_token) { // 200
        log.info("cookie access token: " + !access_token.isEmpty());
        Long userId = internalWebService.getUserId(access_token);
        log.info("userId: {}", userId);
        List<SlimOrderResponseDto> result = orderService.getAll(userId);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> post(@CookieValue String access_token,
                                                 @RequestBody CreateOrderRequestDto dto) { // 200
        Long userId = internalWebService.getUserId(access_token);
        OrderResponseDto result = orderService.save(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOne(@CookieValue String access_token,
                                                   @PathVariable Long id) { // 200
        Long userId = internalWebService.getUserId(access_token);
        OrderResponseDto result = orderService.getOne(userId, id);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchState(@CookieValue String access_token,
                                           @PathVariable Long id,
                                           @RequestBody PatchOrderStateDto dto) { // 200
        Long userId = internalWebService.getUserId(access_token);
        log.info("userId: {}", userId);
        log.info("orderId: {}", id);
        orderService.changeState(userId, id, dto);
        return ResponseEntity.ok().build();
    }
}
