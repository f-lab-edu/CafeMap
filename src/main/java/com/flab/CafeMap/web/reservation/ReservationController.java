package com.flab.CafeMap.web.reservation;

import com.flab.CafeMap.domain.reservation.service.ReservationService;
import com.flab.CafeMap.web.reservation.dto.ReservationSaveRequest;
import com.flab.CafeMap.web.reservation.dto.ReservationSaveResponse;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationSaveResponse> createReservation(
        @RequestBody @Validated ReservationSaveRequest reservationSaveRequest, HttpSession session) {
        ReservationSaveResponse reservationSaveResponse = reservationService.addReservation(reservationSaveRequest, session.getAttribute("loginId").toString());
        return new ResponseEntity<>(reservationSaveResponse, HttpStatus.CREATED);
    }
}
