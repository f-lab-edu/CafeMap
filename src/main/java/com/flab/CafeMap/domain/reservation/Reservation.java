package com.flab.CafeMap.domain.reservation;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Getter : getter 메소드를 자동으로 생성
 * @Builder : 빌더 패턴을 사용할 수 있게 도와주는 애노테이션
 * @NoArgsConstructor : 필드가 없는 기본 생성자를 만들어준다.
 * @AllArgsConstructor  : 모든 필드를 받는 생성자를 만들어준다.
 */

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
