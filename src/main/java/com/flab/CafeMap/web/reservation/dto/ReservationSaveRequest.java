package com.flab.CafeMap.web.reservation.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationSaveRequest {

    @NotNull
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Long cafeId;

    @NotNull
    private LocalDateTime reservationTime;

    @NotNull
    private LocalDateTime createAt;
}
