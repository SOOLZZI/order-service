package com.haruhanjan.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {
    @KafkaListener(topics = "alcohol", groupId = "alcohol")
    public void consume(String message) {
       log.info("{}", message);
    }
}
