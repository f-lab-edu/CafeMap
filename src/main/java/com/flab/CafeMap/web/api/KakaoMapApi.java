package com.flab.CafeMap.web.api;

import com.flab.CafeMap.web.user.dto.kakao.KakaoMapApiRequest;
import com.flab.CafeMap.web.user.dto.kakao.KakaoMapApiResponse;

import java.net.URI;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @Slf4j : 로깅을 위한 추상화 라이브러리
 * @Component : 런타임 시 자동으로 빈을 등록해준다.
 * @RequiredArgsConstructor : final 필드에 대해 생성자 생성
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoMapApi {

    private final WebClient webClient;

    @Value("${KAKAO_SECRET}")
    private String kakaoSecretKey;

    public static final String kakaoHeader = "KakaoAK ";

    public static final String kakaoHost = "https://dapi.kakao.com";

    public static final String kakaoURL = "/v2/local/search/category.json";

    public ResponseEntity<KakaoMapApiResponse> getAddressByCoordinates(
            KakaoMapApiRequest kakaoMapApiRequest) {

        URI url = UriComponentsBuilder.fromHttpUrl(kakaoHost + kakaoURL)
                .queryParam("category_group_code", "CE7")
                .queryParam("x", kakaoMapApiRequest.getX())
                .queryParam("y", kakaoMapApiRequest.getY())
                .queryParam("radius", 1000) // 1km
                .build()
                .toUri();

        log.info("url : {}", url);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set("Authorization", kakaoHeader + kakaoSecretKey);

        return webClient
                .get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, kakaoHeader + kakaoSecretKey)
                .retrieve()
                .toEntity(KakaoMapApiResponse.class)
                .block();
    }
}

