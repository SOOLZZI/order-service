package com.haruhanjan.orderservice.service;

import com.haruhanjan.orderservice.config.APIConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    // spring web client 호출
    // api call

    private final APIConfig apiConfig;


    public Long getUserId() {
        // 1. spring webClient를 통해서 {baseUri}/api/auth를 호출
        // 2. 호출 반환값(json)을 해석해서 변환
        // 3. 해석값 중 Id 추출 후 반환
    }
}
