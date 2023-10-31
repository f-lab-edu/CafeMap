package com.flab.CafeMap.domain.reservation.service;

import com.flab.CafeMap.domain.cafe.Cafe;
import com.flab.CafeMap.domain.cafe.dao.CafeMapper;
import com.flab.CafeMap.domain.reservation.Reservation;
import com.flab.CafeMap.domain.reservation.ReservationStatus;
import com.flab.CafeMap.domain.reservation.dao.ReservationMapper;
import com.flab.CafeMap.domain.reservation.exception.CafeNotFoundException;
import com.flab.CafeMap.domain.reservation.exception.UserIdNotFoundException;
import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.dao.UserMapper;
import com.flab.CafeMap.web.reservation.dto.ReservationSaveRequest;
import com.flab.CafeMap.web.reservation.dto.ReservationSaveResponse;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationMapper reservationMapper;
    private final UserMapper userMapper;
    private final CafeMapper cafeMapper;

    @Transactional
    public ReservationSaveResponse addReservation(ReservationSaveRequest reservationSaveRequest, String loginId) {
        User user = userMapper.selectUserByLoginId(loginId)
            .orElseThrow(() -> new UserIdNotFoundException());

        Long cafeId = reservationSaveRequest.getCafeId();
        Cafe cafe = cafeMapper.selectCafeById(cafeId)
            .orElseThrow(() -> new CafeNotFoundException());

        userMapper.insertUser(user);

        Reservation reservation = Reservation.builder()
            .userId(user.getId())
            .cafeId(cafe.getId())
            .reservationStatus(ReservationStatus.CONFIRMED)
            .createdAt(LocalDateTime.now())
            .build();

        reservationMapper.insertReservation(reservation);

        return ReservationSaveResponse.builder()
            .userId(user.getId())
            .cafeId(cafe.getId())
            .reservationTime(reservation.getCreatedAt())
            .build();
    }
}
