package com.haruhanjan.orderservice.controller;

import com.haruhanjan.orderservice.dto.*;
import com.haruhanjan.orderservice.service.CartService;
import com.haruhanjan.orderservice.service.InternalWebService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final InternalWebService internalWebService;

    @GetMapping
    public ResponseEntity<List<CartResponseDto>> getAll(@CookieValue String access_token) {
        Long userId = internalWebService.getUserId(access_token);
        List<CartResponseDto> result = cartService.getAll(userId);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<CartResponseDto> post(@CookieValue String access_token,
                                                @RequestBody CartRequestDto dto) { // 200
        Long userId = internalWebService.getUserId(access_token);
        CartResponseDto result = cartService.save(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping
    public ResponseEntity<CartResponseDto> delete(@CookieValue String access_token,
                                                @RequestBody CartRequestDto dto) { // 200
        Long userId = internalWebService.getUserId(access_token);
        CartResponseDto result = cartService.delete(userId);
        return ResponseEntity.ok(result);
    }
}
