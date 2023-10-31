package com.flab.CafeMap.domain.cafe.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.flab.CafeMap.domain.cafe.Cafe;
import com.flab.CafeMap.web.cafe.dto.CafeSaveRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * @SpringBootTest : SpringBoot 통합테스트에 사용되는 애노테이션으로 @SpringBootApplication을 찾아가 하위의 모든 빈을 스캔한다.
 * @ActiveProfiles : 스프링 컨테이터 실행 시 실행 환경을 지정해주는 애노테이션으로, 테스트 시 특정 빈을 로드
 * @Transactional : 선언적 트랜잭션에 사용되는 애노테이션으로 테스트 이후 롤백
 */

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CafeServiceTest {

    @Autowired
    private CafeService cafeService;

    @Test
    @DisplayName("카페 등록 테스트")
    void addCafe() {
        //given, when
        Cafe cafe = cafeService.addCafe(CafeSaveRequest.builder()
            .loginId("loginId")
            .cafeId("testId")
            .streetAddress("서울시 강남구")
            .detailAddress("논현동 207")
            .name("testName")
            .latitude(37.513863998587)
            .longitude(127.0312783056)
            .createdBy("user1")
            .build());

        //then
        assertThat(cafe.getCafeId()).isEqualTo("testId");
        assertThat(cafe.getName()).isEqualTo("testName");
        assertThat(cafe.getLatitude()).isEqualTo(37.513863998587);
        assertThat(cafe.getLongitude()).isEqualTo(127.0312783056);
    }
}
