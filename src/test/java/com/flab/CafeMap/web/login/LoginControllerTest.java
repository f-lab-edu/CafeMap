package com.flab.CafeMap.web.login;

import static com.flab.CafeMap.domain.login.service.LoginService.LOGIN_SESSION;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.CafeMap.domain.login.service.LoginService;
import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.web.login.dto.LoginRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * @WebMvcTest : 컨트롤러 테스트 시에 테스트에 필요한 빈을 지정하는 애노테이션
 * @ActiveProfiles : 스프링 컨테이터 실행 시 실행 환경을 지정해주는 애노테이션으로, 테스트 시 특정 빈을 로드
 */

@WebMvcTest(LoginController.class)
@ActiveProfiles("test")
class LoginControllerTest {

    @MockBean
    LoginService loginService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("로그인 성공 시 200 상태코드 반환")
    void login() throws Exception {
        //given
        LoginRequest loginRequest = createLoginRequest();
        User user = User.builder()
            .loginId("testLoginId")
            .name("testName")
            .build();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(LOGIN_SESSION, "test");

        Mockito.when(loginService.login(any(), any())).thenReturn(user);

        //when
        mockMvc.perform(post("/login")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
                .accept(MediaType.APPLICATION_JSON))
            //then
            .andDo(print()).andExpect(status().isOk());
    }

    @Test
    @DisplayName("중복 로그인 테스트")
    void duplicatedLogin() throws Exception {
        //given
        LoginRequest loginRequest = createLoginRequest();
        User user = User.builder()
            .loginId("testLoginId")
            .name("testName")
            .build();

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(LOGIN_SESSION, "test");

        Mockito.when(loginService.login(any(), any())).thenReturn(user);

        mockMvc.perform(post("/login")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print()).andExpect(status().isOk());

        //when
        mockMvc.perform(post("/login")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // Then
            .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("로그아웃 성공 시 200 상태코드 반환")
    void logout() throws Exception {
        //given
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(LOGIN_SESSION, "test");

        Mockito.doNothing().when(loginService).logout(any());

        //when
        mockMvc.perform(post("/logout")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            //then
            .andDo(print()).andExpect(status().isOk());
    }

    private LoginRequest createLoginRequest() {
        return LoginRequest.builder()
            .loginId("testLoginId")
            .password("testPassword")
            .build();
    }
}
