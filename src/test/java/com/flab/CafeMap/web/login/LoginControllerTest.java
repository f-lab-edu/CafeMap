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
    UserService userService;

    @MockBean
    LoginService loginService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("로그인 성공 시 201 상태코드 반환")
    void login() throws Exception {
        UserSaveRequest userSaveRequest = createUser();
        userService.addUser(userSaveRequest);

        LoginRequest loginRequest = new LoginRequest(userSaveRequest.getLoginId(),
            "testPassword");
        //when
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
                .accept(MediaType.APPLICATION_JSON))
            //then
            .andDo(print()).andExpect(status().isCreated())
            .andExpect(jsonPath("$.loginId").value(userSaveRequest.getLoginId()))
            .andExpect(jsonPath("$.name").value(userSaveRequest.getPassword()));
    }


    private UserSaveRequest createUser() {
        return UserSaveRequest.builder()
            .loginId("userControllerTestId")
            .name("testName")
            .password("testPassword")
            .phoneNumber("01012345678")
            .build();
    }
}
