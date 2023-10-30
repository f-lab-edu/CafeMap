package com.flab.CafeMap.web.cafe;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.CafeMap.domain.cafe.Cafe;
import com.flab.CafeMap.domain.cafe.service.CafeService;
import com.flab.CafeMap.web.cafe.dto.CafeSaveRequest;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @WebMvcTest : 컨트롤러 테스트 시에 테스트에 필요한 빈을 지정하는 애노테이션
 * @ActiveProfiles : 스프링 컨테이터 실행 시 실행 환경을 지정해주는 애노테이션으로, 테스트 시 특정 빈을 로드
 */

@WebMvcTest
@ActiveProfiles("test")
@Disabled
class CafeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CafeService cafeService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("카페 등록 성공 테스트")
    void addCafe() throws Exception {
        //given
        CafeSaveRequest cafeSaveRequest = CafeSaveRequest.builder()
            .loginId("loginId")
            .cafeId("cafeId")
            .streetAddress("서울시 강남구")
            .detailAddress("논현동 207")
            .name("testName")
            .latitude(37.513863998587)
            .longitude(127.0312783056)
            .createdBy("user1")
            .build();

        Cafe cafe = Cafe.builder()
            .id(1L)
            .cafeId(cafeSaveRequest.getCafeId())
            .name(cafeSaveRequest.getName())
            .latitude(cafeSaveRequest.getLatitude())
            .longitude(cafeSaveRequest.getLongitude())
            .createdAt(LocalDateTime.now())
            .build();

        Mockito.when(cafeService.addCafe(Mockito.any())).thenReturn(cafe);

        //when
        mockMvc.perform(post("/cafes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cafeSaveRequest))
                .accept(MediaType.APPLICATION_JSON))
            //then
            .andDo(print()).andExpect(status().isCreated());
    }
}
