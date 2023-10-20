package com.flab.CafeMap.web.api;

import com.flab.CafeMap.web.user.dto.kakao.KakaoMapApiRequest;
import com.flab.CafeMap.web.user.dto.kakao.KakaoMapApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

/**
 * @SpringBootTest : SpringBoot 통합테스트에 사용되는 애노테이션으로 @SpringBootApplication을 찾아가 하위의 모든 빈을 스캔한다.
 * @ActiveProfiles : 스프링 컨테이터 실행 시 실행 환경을 지정해주는 애노테이션으로, 테스트 시 특정 빈을 로드
 */

@SpringBootTest
@ActiveProfiles("test")
class KakaoMapApiTest {

    @Autowired
    KakaoMapApi kakaoMapApi;

    @Test
    @DisplayName("카테고리와 좌표 정보로 주소 받아오는 API 테스트")
    void getAddressByCoordinates() {

        //given
        ResponseEntity<KakaoMapApiResponse> response = kakaoMapApi.getAddressByCoordinates(
            KakaoMapApiRequest.builder()
                .category_group_code("CE7")
                .x("127.423084873712")
                .y("37.0789561558879")
                .build());

        //when
        KakaoMapApiResponse result = response.getBody();

        //then
        Assertions.assertNotNull(result);
    }
}
