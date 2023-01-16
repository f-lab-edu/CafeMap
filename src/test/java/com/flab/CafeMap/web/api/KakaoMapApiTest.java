package com.flab.CafeMap.web.api;

import com.flab.CafeMap.web.user.dto.kakao.KakaoMapApiRequest;
import com.flab.CafeMap.web.user.dto.kakao.KakaoMapApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class KakaoMapApiTest {

    @Autowired
    KakaoMapApi kakaoMapApi;

    @Test
    @DisplayName("카테고리와 좌표 정보로 주소 받아오는 API 테스트")
    void getAddressByCoordinates() {

        //given
        KakaoMapApiRequest kakaoMapApiRequest = KakaoMapApiRequest.builder()
            .category_group_code("CE7")
            .x("127")
            .y("37")
            .build();
        
        //when
        KakaoMapApiResponse result = kakaoMapApi.getAddressByCoordinates(kakaoMapApiRequest).getBody();

        //then
        Assertions.assertNotNull(result);
    }
}
