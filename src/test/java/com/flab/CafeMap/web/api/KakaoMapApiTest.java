package com.flab.CafeMap.web.api;

import static org.junit.jupiter.api.Assertions.*;

import com.flab.CafeMap.web.user.dto.kakao.KakaoMapApiRequest;
import com.flab.CafeMap.web.user.dto.kakao.KakaoMapApiResponse;
import org.junit.jupiter.api.Assertions;
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
    void getAddressByCoordinates() {

        //given
        KakaoMapApiRequest kakaoMapApiRequest = KakaoMapApiRequest.builder()
            .category_group_code("CE7")
            .x("127")
            .y("37")
            .radius(19)
            .build();
        
        //when
        KakaoMapApiResponse result = kakaoMapApi.getAddressByCoordinates(kakaoMapApiRequest).getBody();

        //then
        Assertions.assertNotNull(result);
    }
}
