package com.haruhanjan.orderservice.repository;

import com.haruhanjan.orderservice.entity.Alcohol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlcoholRepository extends JpaRepository<Alcohol, Long> {
    public Optional<Alcohol> findByOriginId(Long originId);
}
