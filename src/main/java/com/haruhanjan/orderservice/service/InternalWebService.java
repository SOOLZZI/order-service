package com.haruhanjan.orderservice.service;

import com.haruhanjan.orderservice.dto.AlcoholResponse;
import com.haruhanjan.orderservice.dto.UserResponse;
import com.haruhanjan.orderservice.repository.AlcoholRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;
import java.util.Optional;

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
    // getAlcohol -> db 확인 -> 없으면 getAlcoholById 호출
//    public AlcoholResponse getAlcohol(Long id){
//        alcoholRepository.findById(id)
//    }

    public AlcoholResponse getAlcoholById(Long id){
        AlcoholResponse alcoholResponse = webClient.get()
                .uri("/api/alcohol/"+id.toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AlcoholResponse.class)
                .blockOptional().orElseThrow(EntityNotFoundException::new);

        alcoholRepository.save(alcoholResponse.toEntity());

        return alcoholResponse;
    }

}