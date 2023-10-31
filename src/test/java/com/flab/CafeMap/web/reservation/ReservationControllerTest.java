package com.flab.CafeMap.web.reservation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.CafeMap.domain.cafe.service.CafeService;
import com.flab.CafeMap.domain.reservation.service.ReservationService;
import com.flab.CafeMap.web.reservation.dto.ReservationSaveRequest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ReservationController.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Disabled
class ReservationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CafeService cafeService;

    @MockBean
    ReservationService reservationService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("예약 생성 테스트")
    void createReservation() throws Exception {
        //given
        ReservationSaveRequest reservationRequest = ReservationSaveRequest.builder()
            .cafeId(1L)
            .build();

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("loginId", "test");

        //when
        mockMvc.perform(post("/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reservationRequest))
                .accept(MediaType.APPLICATION_JSON))
            //then
            .andExpect(status().isCreated());
    }
}

