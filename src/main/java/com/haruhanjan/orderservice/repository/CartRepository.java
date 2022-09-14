package com.haruhanjan.orderservice.repository;

import com.haruhanjan.orderservice.entity.Cart;
import com.haruhanjan.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserId(Long userId);

}
