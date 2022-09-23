package com.haruhanjan.orderservice.service;

import com.haruhanjan.orderservice.dto.AlcoholResponse;
import com.haruhanjan.orderservice.entity.Alcohol;
import com.haruhanjan.orderservice.repository.AlcoholRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class AlcoholService {

    private final AlcoholRepository alcoholRepository;
    private final InternalWebService internalWebService;

    @KafkaListener(topics = "alcohol", groupId = "alcohol")
    public void consume(String message) { //1

        AlcoholResponse alcohol = internalWebService
                .callApiGetAlcoholById(Long.parseLong(message));

        alcoholRepository.save(alcohol.toEntity());
    }

    public Alcohol findById(Long id) {
        return alcoholRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

}
