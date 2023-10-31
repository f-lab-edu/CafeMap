package com.flab.CafeMap.domain.reservation.dao;

import com.flab.CafeMap.domain.reservation.Reservation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {

    int insertReservation(Reservation reservation);
}
