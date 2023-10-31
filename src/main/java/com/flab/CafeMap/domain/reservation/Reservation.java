package com.flab.CafeMap.domain.reservation;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    private Long id;
    private Long userId;
    private Long cafeId;
    private String address;
    private ReservationStatus reservationStatus;
    private LocalDateTime createdAt;
}
