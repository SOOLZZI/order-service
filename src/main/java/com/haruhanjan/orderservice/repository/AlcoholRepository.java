package com.haruhanjan.orderservice.repository;

import com.haruhanjan.orderservice.entity.Alcohol_copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlcoholRepository extends JpaRepository<Alcohol_copy, Long> {
}
