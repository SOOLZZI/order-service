package com.haruhanjan.orderservice.service;

import com.haruhanjan.orderservice.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final WebClient webClient;

    public Long getUserId() {
        // 1. spring webClient를 통해서 {baseUri}/api/auth를 호출
        Optional<UserResponse> userResponse = webClient.get()
                .uri("/api/auth")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .blockOptional();//mono 반환

        // 2. 해석값 중 Id 추출 후 반환
        return userResponse
                .orElseThrow(RuntimeException::new)
                .getId();
    }
}
