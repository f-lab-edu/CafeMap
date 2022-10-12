package com.flab.CafeMap.web.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.CafeMap.domain.user.service.UserService;
import com.flab.CafeMap.web.user.dto.UserSaveRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("회원가입 성공 시 201 상태코드 반환")
    void addUser() throws Exception {
        //given
        UserSaveRequest userSaveRequest = createUser();

        //when
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userSaveRequest))
                .accept(MediaType.APPLICATION_JSON))

            //then
            .andDo(print()).andExpect(status().isCreated())
            .andExpect(jsonPath("$.loginId").value(userSaveRequest.getLoginId()))
            .andExpect(jsonPath("$.name").value(userSaveRequest.getName()))
            .andExpect(jsonPath("$.phoneNumber").value(userSaveRequest.getPhoneNumber()));
    }

    @Test
    @DisplayName("회원가입 시 필수 값이 누락되었을 때 400 상태코드 반환")
    void addUserFail() throws Exception {
        //given
        UserSaveRequest userSaveRequest = UserSaveRequest.builder().loginId("testAddUserFail")
            .name("testName").password("testPassword").build();

        //when
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userSaveRequest))
                .accept(MediaType.APPLICATION_JSON))

            //then
            .andDo(print()).andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("FieldNotFoundException"));
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

