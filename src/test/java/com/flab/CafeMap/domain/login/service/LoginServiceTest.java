package com.flab.CafeMap.domain.login.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.flab.CafeMap.domain.login.exception.DuplicatedLoginSessionException;
import com.flab.CafeMap.domain.login.exception.InvalidPasswordException;
import com.flab.CafeMap.domain.login.exception.LoginSessionNotFoundException;
import com.flab.CafeMap.domain.user.exception.UserNotFoundException;
import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.service.UserService;
import com.flab.CafeMap.web.login.dto.LoginRequest;
import com.flab.CafeMap.web.user.dto.UserSaveRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * @SpringBootTest : SpringBoot 통합테스트에 사용되는 애노테이션으로 @SpringBootApplication을 찾아가 하위의 모든 빈을 스캔한다.
 * @ActiveProfiles : 스프링 컨테이터 실행 시 실행 환경을 지정해주는 애노테이션으로, 테스트 시 특정 빈을 로드
 * @Transactional : 선언적 트랜잭션에 사용되는 애노테이션으로 테스트 이후 롤백
 */

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class LoginServiceTest {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @Test
    @DisplayName("로그인 성공 테스트")
    void login() {
        //given
        UserSaveRequest userSaveRequest = createUser();
        userService.addUser(userSaveRequest);

        MockHttpSession session = new MockHttpSession();
        LoginRequest loginRequest = new LoginRequest(userSaveRequest.getLoginId(),
            "testPassword");

        //when
        User user = loginService.login(loginRequest, session);

        //then
        assertThat(user.getLoginId()).isEqualTo(userSaveRequest.getLoginId());
        assertThat(user.getName()).isEqualTo(userSaveRequest.getName());
        assertThat(user.getPassword()).isEqualTo(userSaveRequest.getPassword());
        assertThat(user.getPhoneNumber()).isEqualTo(userSaveRequest.getPhoneNumber());
    }

    @Test
    @DisplayName("이미 해당 아이디로 로그인 되어있을 때 예외 호출 테스트")
    void duplicatedLoginSessionExceptionTest() {
        //given
        UserSaveRequest userSaveRequest = createUser();
        userService.addUser(userSaveRequest);

        MockHttpSession session = new MockHttpSession();
        LoginRequest loginRequest = new LoginRequest(userSaveRequest.getLoginId(), "testPassword");

        //when
        loginService.login(loginRequest, session);

        //then
        assertThatThrownBy(() -> {
            loginService.login(loginRequest, session);
        }).isInstanceOf(DuplicatedLoginSessionException.class);
    }

    @Test
    @DisplayName("아이디를 찾을 수 없을 때 예외 호출 테스트")
    void LoginIdNotFoundExceptionTest() {
        //given
        LoginRequest loginRequest = new LoginRequest("testLoginId", "testPassword");
        MockHttpSession session = new MockHttpSession();

        //when, then
        assertThatThrownBy(() -> {
            loginService.login(loginRequest, session);
        }).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    @DisplayName("비밀번호 틀릴 때 예외 호출되는지 확인")
    void InvalidPasswordExceptionTest() {
        //given
        MockHttpSession session = new MockHttpSession();
        UserSaveRequest userSaveRequest = createUser();
        userService.addUser(userSaveRequest);

        LoginRequest loginRequest = new LoginRequest(userSaveRequest.getLoginId(), "testPassword2");

        //when, then
        assertThatThrownBy(() -> {
            loginService.login(loginRequest, session);
        }).isInstanceOf(InvalidPasswordException.class);
    }

    @Test
    @DisplayName("로그아웃 테스트")
    void logout() {
        //given
        UserSaveRequest userSaveRequest = createUser();
        userService.addUser(userSaveRequest);

        MockHttpSession session = new MockHttpSession();
        LoginRequest loginRequest = new LoginRequest(userSaveRequest.getLoginId(),
            "testPassword");

        loginService.login(loginRequest, session);

        //when
        loginService.logout(session);

        //then
        assertThat(session.isInvalid()).isTrue();
    }

    @Test
    @DisplayName("로그아웃 시 세션이 존재하지 않는 경우 예외 호출 테스트")
    void logoutFailed() {
        //given
        UserSaveRequest userSaveRequest = createUser();
        userService.addUser(userSaveRequest);

        MockHttpSession session = new MockHttpSession();

        //when, then
        assertThatThrownBy(() -> {
            loginService.logout(session);
        }).isInstanceOf(LoginSessionNotFoundException.class);
    }

    @Test
    @DisplayName("이미 로그인 중일 때 DuplicatedLoginSessionException 예외 호출 테스트")
    void DuplicatedSessionExceptionTest() {
        //given
        UserSaveRequest userSaveRequest = createUser();
        userService.addUser(userSaveRequest);

        MockHttpSession session = new MockHttpSession();
        LoginRequest loginRequest = new LoginRequest(userSaveRequest.getLoginId(), "testPassword");

        //when
        loginService.login(loginRequest, session);

        //then
        assertThatThrownBy(() -> {
            loginService.login(loginRequest, session);
        }).isInstanceOf(DuplicatedLoginSessionException.class);
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
