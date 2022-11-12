package com.flab.CafeMap.web.user;

import static com.flab.CafeMap.domain.login.service.LoginService.LOGIN_SESSION;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.service.UserService;
import com.flab.CafeMap.web.interceptor.LoginInterceptor;
import com.flab.CafeMap.web.user.dto.UserSaveRequest;
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


/**
 * @WebMvcTest : 컨트롤러 테스트 시에 테스트에 필요한 빈을 지정하는 애노테이션
 * @ActiveProfiles : 스프링 컨테이터 실행 시 실행 환경을 지정해주는 애노테이션으로, 테스트 시 특정 빈을 로드
 */

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
class UserControllerTest {

    @MockBean
    UserService userService;

    @MockBean
    LoginInterceptor loginInterceptor;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("회원가입 성공 테스트")
    void addUser() throws Exception {
        //given
        UserSaveRequest userSaveRequest = createUser();
        User user = User.builder()
            .id(1L)
            .loginId("testId")
            .name("testName")
            .password("testPassword")
            .phoneNumber("01012345678")
            .build();

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(LOGIN_SESSION, "test");

        Mockito.when(userService.addUser(any())).thenReturn(user);
        Mockito.when(loginInterceptor.preHandle(any(), any(), any())).thenReturn(true);

        //when
        mockMvc.perform(post("/users")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userSaveRequest))
                .accept(MediaType.APPLICATION_JSON))
            //then
            .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("회원가입 시 필수 값이 누락")
    void addUserFail() throws Exception {
        //given
        UserSaveRequest userSaveRequest = UserSaveRequest.builder()
            .loginId("testLoginId")
            .name("testName")
            .password("testPassword").build();

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(LOGIN_SESSION, "test");

        //when
        mockMvc.perform(post("/users")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userSaveRequest))
                .accept(MediaType.APPLICATION_JSON))
            //then
            .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("회원 아이디로 회원 정보 조회")
    void getUser() throws Exception {
        //given
        User user = User.builder()
            .id(1L)
            .loginId("testId")
            .name("testName")
            .password("testPassword")
            .phoneNumber("01012345678")
            .build();

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(LOGIN_SESSION, "test");

        Mockito.when(userService.addUser(any())).thenReturn(user);
        Mockito.when(userService.findUserById(1L)).thenReturn(user);

        //when
        mockMvc.perform(get("/users/" + user.getId())
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            //then
            .andDo(print()).andExpect(status().isOk());
    }

    private UserSaveRequest createUser() {
        return UserSaveRequest.builder()
            .id(1L)
            .loginId("userControllerTestId")
            .name("testName")
            .password("testPassword")
            .phoneNumber("01012345678")
            .build();
    }
}

