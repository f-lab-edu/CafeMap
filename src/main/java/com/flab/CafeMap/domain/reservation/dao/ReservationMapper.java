package com.flab.CafeMap.domain.reservation.dao;

import com.flab.CafeMap.domain.reservation.Reservation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Mapper : MyBatis 매핑 xml의 SQL을 호출하기 위한 인터페이스
 */

@Mapper
public interface ReservationMapper {

    int insertReservation(Reservation reservation);
}
