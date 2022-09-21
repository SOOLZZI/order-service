package com.haruhanjan.orderservice.controller;

import com.haruhanjan.orderservice.dto.*;
import com.haruhanjan.orderservice.service.CartService;
import com.haruhanjan.orderservice.service.InternalWebService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final InternalWebService internalWebService;

    @GetMapping
    public ResponseEntity<CartResponseDto> getAll(@CookieValue String access_token) {
        Long userId = internalWebService.getUserId(access_token);
        CartResponseDto result = cartService.getAll(userId);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<CartResponseDto> post(@CookieValue String access_token,
                                                @RequestBody CreateItemRequestDto dto) {
        Long userId = internalWebService.getUserId(access_token);
        cartService.save(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{cartId}")
    public ResponseEntity<CartResponseDto> delete(@PathVariable Long cartId) { // 200
        cartService.delete(cartId);
        return ResponseEntity.ok().build();
    }
}
