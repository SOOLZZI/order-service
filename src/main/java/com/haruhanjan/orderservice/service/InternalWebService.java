package com.haruhanjan.orderservice.service;

import com.haruhanjan.orderservice.dto.AlcoholResponse;
import com.haruhanjan.orderservice.dto.UserResponse;
import com.haruhanjan.orderservice.entity.Alcohol;
import com.haruhanjan.orderservice.repository.AlcoholRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class InternalWebService {

    private final WebClient webClient;
    private final AlcoholRepository alcoholRepository;

    public Long getUserId(String access_token) {
        // 1. spring webClient를 통해서 {baseUri}/api/auth를 호출
        Optional<UserResponse> userResponse = webClient.get()
                .uri("/api/auth")
                .accept(MediaType.APPLICATION_JSON)
                .cookie("access_token", access_token)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .blockOptional();//mono 반환

        // 2. 해석값 중 Id 추출 후 반환
        return userResponse
                .orElseThrow(RuntimeException::new)
                .getId();
    }

    public Alcohol getAlcoholById(Long id){
        AlcoholResponse alcoholResponse = webClient.get()
                .uri("/api/alcohol/"+id.toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AlcoholResponse.class)
                .blockOptional().orElseThrow(EntityNotFoundException::new);

        return alcoholRepository.save(alcoholResponse.toEntity());
    }

}