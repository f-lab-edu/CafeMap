package com.flab.CafeMap.web.login;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


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
        Mockito.when(loginService.login(any(), any())).thenReturn(user);

        //when
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
                .accept(MediaType.APPLICATION_JSON))
            //then
            .andDo(print()).andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그아웃 성공 시 200 상태코드 반환")
    void logout() throws Exception {
        //given
        Mockito.doNothing().when(loginService).logout(any());

        //when
        mockMvc.perform(post("/logout")
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
