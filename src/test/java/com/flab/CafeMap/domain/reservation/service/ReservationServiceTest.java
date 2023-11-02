package com.flab.CafeMap.domain.reservation.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.flab.CafeMap.domain.cafe.Cafe;
import com.flab.CafeMap.domain.cafe.dao.CafeMapper;
import com.flab.CafeMap.domain.reservation.exception.CafeNotFoundException;
import com.flab.CafeMap.domain.reservation.exception.UserIdNotFoundException;
import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.UserAddress;
import com.flab.CafeMap.domain.user.dao.UserMapper;
import com.flab.CafeMap.web.reservation.dto.ReservationSaveRequest;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
@Disabled
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @MockBean
    private UserMapper userMapper;

    @Test
    @DisplayName("예약 insert 실패 시 예외 호출 테스트")
    void addReservationFailed() {
        //given
        User user = User.builder()
            .id(1L)
            .loginId("testUser")
            .name("testName")
            .password("testPassword")
            .phoneNumber("01012345678")
            .createdAt(LocalDateTime.now())
            .createdBy("test")
            .build();

        Cafe cafe = Cafe.builder()
            .id(1L)
            .cafeId("cafeId")
            .name("testCafe")
            .latitude(37.513863998587)
            .longitude(127.0312783056)
            .build();

        when(userMapper.selectUserById(1L)).thenReturn(Optional.of(user));

        //when
        ReservationSaveRequest reservationSaveRequest = ReservationSaveRequest.builder()
            .userId(user.getId())
            .cafeId(1L)
            .reservationTime(LocalDateTime.now())
            .createAt(LocalDateTime.now())
            .build();

        //then
        assertThrows(UserIdNotFoundException.class, () -> {
            reservationService.addReservation(reservationSaveRequest, user.getLoginId());
        });
    }
}

