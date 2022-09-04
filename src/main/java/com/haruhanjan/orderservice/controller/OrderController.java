package com.haruhanjan.orderservice.controller;

import com.haruhanjan.orderservice.dto.CreateOrderRequestDto;
import com.haruhanjan.orderservice.dto.CreateOrderResponseDto;
import com.haruhanjan.orderservice.dto.PatchOrderStateDto;
import com.haruhanjan.orderservice.service.InternalWebService;
import com.haruhanjan.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    private final InternalWebService internalWebService;

    @GetMapping
    public ResponseEntity<List<CreateOrderResponseDto>> getAll(@CookieValue String access_token) {
        Long userId = internalWebService.getUserId(access_token);
        List<CreateOrderResponseDto> result = orderService.get(userId);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponseDto> post(@CookieValue String access_token,
                                                       @RequestBody CreateOrderRequestDto dto) {
        Long userId = internalWebService.getUserId(access_token);
        CreateOrderResponseDto result = orderService.save(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateOrderResponseDto> getOne(@CookieValue String access_token,
                                                         @PathVariable Long id) {
        Long userId = internalWebService.getUserId(access_token);
        CreateOrderResponseDto result = orderService.getOne(userId, id);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchState(@CookieValue String access_token,
                                           @PathVariable Long id,
                                           @RequestBody PatchOrderStateDto dto) {
        Long userId = internalWebService.getUserId(access_token);
        orderService.patchState(userId, id, dto);
        return ResponseEntity.ok().build();
    }
}
