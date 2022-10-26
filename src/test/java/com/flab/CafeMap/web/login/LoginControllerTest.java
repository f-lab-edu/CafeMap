package com.flab.CafeMap.web.login;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.CafeMap.domain.login.service.LoginService;
import com.flab.CafeMap.domain.user.service.UserService;
import com.flab.CafeMap.web.login.dto.LoginRequest;
import com.flab.CafeMap.web.user.dto.UserSaveRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @SpringBootTest : SpringBoot 통합테스트에 사용되는 애노테이션으로 @SpringBootApplication을 찾아가 하위의 모든 빈을 스캔한다.
 * @AutoConfigureMockMvc : 컨트롤러를 테스트 할 때 서블릿 컨테이너에 대한 mock 객체 생성
 * @ActiveProfiles : 스프링 컨테이터 실행 시 실행 환경을 지정해주는 애노테이션으로, 테스트 시 특정 빈을 로드
 */

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LoginControllerTest {

    @Mock
    LoginService loginService;

    @Mock
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("로그인 성공 테스트")
    void login() throws Exception {
        //given
        UserSaveRequest userSaveRequest = createUser();
        userService.addUser(userSaveRequest);

        LoginRequest loginRequest = new LoginRequest(userSaveRequest.getLoginId(), "testPassword");

        //when
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
                .accept(MediaType.APPLICATION_JSON))
            //then
            .andExpect(jsonPath("$.loginId").value(userSaveRequest.getLoginId()))
            .andExpect(jsonPath("$.name").value(userSaveRequest.getName()))
            .andExpect(jsonPath("$.phoneNumber").value(userSaveRequest.getPhoneNumber()));
    }

    @Test
    @DisplayName("로그아웃 성공 테스트")
    void logout() throws Exception {
        //given
        UserSaveRequest userSaveRequest = createUser();
        userService.addUser(userSaveRequest);

        MockHttpSession mockHttpSession = new MockHttpSession();
        LoginRequest loginRequest = new LoginRequest(userSaveRequest.getLoginId(), "testPassword");
        loginService.login(loginRequest, mockHttpSession);

        //when
        mockMvc.perform(post("/logout")

        //then
    }

    private UserSaveRequest createUser() {
        return UserSaveRequest.builder()
            .loginId("testLoginId")
            .name("testName")
            .password("testPassword")
            .phoneNumber("testPhoneNumber")
            .build();
    }

}
