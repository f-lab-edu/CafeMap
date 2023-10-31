package com.flab.CafeMap.web.cafe;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.CafeMap.domain.cafe.Cafe;
import com.flab.CafeMap.domain.cafe.service.CafeService;
import com.flab.CafeMap.web.api.KakaoMapApi;
import com.flab.CafeMap.web.cafe.dto.CafeSaveRequest;
import com.flab.CafeMap.web.cafe.dto.CafeSaveResponse;
import com.flab.CafeMap.web.user.dto.kakao.KakaoMapApiRequest;
import com.flab.CafeMap.web.user.dto.kakao.KakaoMapApiResponse;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    KakaoMapApi kakaoMapApi;

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
            .longitude(127.05897078335246)
            .latitude(37.506051888130386)
            .build();

        Cafe cafe = Cafe.builder()
            .id(1L)
            .name("testCafe")
            .latitude(cafeSaveRequest.getLatitude())
            .longitude(cafeSaveRequest.getLongitude())
            .createdAt(LocalDateTime.now())
            .build();

        CafeSaveResponse cafeSaveResponse = CafeSaveResponse.from(cafe);

        CafeService cafeService = Mockito.mock(CafeService.class);
        Mockito.when(cafeService.addCafe(Mockito.any())).thenReturn(cafe);

        //when
        mockMvc.perform(post("/cafes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cafeSaveRequest))
                .accept(MediaType.APPLICATION_JSON)
            )
            //then
            .andExpect(status().isOk());
    }
}
