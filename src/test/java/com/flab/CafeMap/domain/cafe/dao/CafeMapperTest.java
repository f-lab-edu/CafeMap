package com.flab.CafeMap.domain.cafe.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.flab.CafeMap.domain.cafe.Cafe;
import java.time.LocalDateTime;
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
class CafeMapperTest {

    @Autowired
    private CafeMapper cafeMapper;

    @Test
    @DisplayName("Cafe insert 쿼리 테스트")
    void insertCafe() {

        //given
        Cafe cafe = Cafe.builder()
            .id(1L)
            .cafeId("testId")
            .name("testName")
            .latitude(37.513863998587)
            .longitude(127.0312783056)
            .createdAt(LocalDateTime.now())
            .build();

        //when
        int result = cafeMapper.insertCafe(cafe);

        //then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("id로 Cafe 정상 반환 확인 테스트")
    void selectCafeById() {

        //given
        Cafe cafe = Cafe.builder()
            .id(1L)
            .cafeId("testId")
            .name("testName")
            .latitude(37.513863998587)
            .longitude(127.0312783056)
            .createdAt(LocalDateTime.now())
            .build();

        cafeMapper.insertCafe(cafe);

        //when
        Cafe result = (cafeMapper.selectCafeById(1L)).orElseThrow();

        //then
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("cafeId로 User 정상 반환 확인 테스트")
    void selectCafeByLoginId() {

        //given
        Cafe cafe = Cafe.builder()
            .id(1L)
            .cafeId("testId")
            .name("testName")
            .latitude(37.513863998587)
            .longitude(127.0312783056)
            .createdAt(LocalDateTime.now())
            .build();

        cafeMapper.insertCafe(cafe);

        //when
        Cafe result = (cafeMapper.selectCafeByLoginId("testId")).orElseThrow();

        //then
        assertThat(result.getCafeId()).isEqualTo("testId");
    }
}