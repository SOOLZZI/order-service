package com.haruhanjan.orderservice.service;

import com.haruhanjan.orderservice.dto.*;
import com.haruhanjan.orderservice.entity.Alcohol;
import com.haruhanjan.orderservice.entity.Cart;
import com.haruhanjan.orderservice.entity.Order;
import com.haruhanjan.orderservice.mapper.CartMapper;
import com.haruhanjan.orderservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final AlcoholService alcoholService;
    private final CartMapper cartMapper;

    public CartResponseDto getAll(Long userId) {
        //읽어 왜? 상품의 가격과 개수를 조회 -->
        List<Cart> cartList = cartRepository.findAllByUserId(userId);

        //cart요소들을 우리가 원하는 형식으로 변환 ==> CartItemResponseDTO
        List<CartItemResponseDto> cartItems = cartList.stream()
                .map(cartMapper::toCartResponseDto)
                .collect(Collectors.toList());

        // 총 금액 계산
        int totalPrice = cartItems.stream()
                .mapToInt(ci -> ci.getPrice()).sum();

        return new CartResponseDto(cartItems, totalPrice);
    }

    @Transactional
    public void save(Long userId, @Valid CreateItemRequestDto dto) {
        Alcohol alcohol = alcoholService.findById(dto.getAlcoholId());
        cartRepository.save(cartMapper.toEntity(dto, alcohol, userId));
    }
}