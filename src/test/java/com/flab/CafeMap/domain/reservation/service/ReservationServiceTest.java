package com.flab.CafeMap.domain.reservation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.flab.CafeMap.domain.cafe.Cafe;
import com.flab.CafeMap.domain.cafe.dao.CafeMapper;
import com.flab.CafeMap.domain.reservation.exception.CafeNotFoundException;
import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.UserAddress;
import com.flab.CafeMap.domain.user.dao.UserMapper;
import com.flab.CafeMap.domain.user.service.UserAddressService;
import com.flab.CafeMap.web.reservation.dto.ReservationSaveRequest;
import com.flab.CafeMap.web.reservation.dto.ReservationSaveResponse;
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

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Disabled
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private CafeMapper cafeMapper;

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

        UserAddress userAddress = UserAddress.builder()
            .id(1L)
            .loginId("testUser")
            .streetAddress("123 Main St")
            .detailAddress("Apt 101")
            .latitude(37.12345)
            .longitude(127.54321)
            .createdAt(LocalDateTime.now())
            .createdBy("test")
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
        assertThrows(CafeNotFoundException.class, () -> {
            reservationService.addReservation(reservationSaveRequest, user.getId());
        });
    }
}

