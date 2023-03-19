package com.flab.CafeMap.domain.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.flab.CafeMap.domain.login.exception.UserNotFoundException;
import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.web.user.dto.UserPatchRequest;
import com.flab.CafeMap.web.user.dto.UserSaveRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("회원가입 테스트")
    void addUser() {
        //given
        userService.addUser(UserSaveRequest.builder()
            .loginId("testId")
            .name("testName")
            .password("testPassword")
            .phoneNumber("01012345678")
            .build());

        //when
        User user = userService.findUser("testId");

        //then
        assertThat(user.getLoginId()).isEqualTo("testId");
        assertThat(user.getName()).isEqualTo("testName");
        assertThat(user.getPhoneNumber()).isEqualTo("01012345678");
    }

    @Test
    @DisplayName("loginId로 회원 정보 조회 테스트")
    void findUser() {
        //given
        userService.addUser(UserSaveRequest.builder()
            .loginId("testId")
            .password("testPassword")
            .name("testName")
            .phoneNumber("01012345678")
            .build());

        //when
        User findUser = userService.findUser("testId");

        //then
        assertThat(findUser.getLoginId()).isEqualTo("testId");
    }

    @Test
    @DisplayName("id로 회원 정보 조회 테스트")
    void findUserById() {
        //given
        userService.addUser(UserSaveRequest.builder()
            .id(1L)
            .loginId("testId")
            .password("testPassword")
            .name("testName")
            .phoneNumber("01012345678")
            .build());

        //when
        User findUser = userService.findUserById(1L);

        //then
        assertThat(findUser.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("loginId로 회원 정보 조회 실패 시 예외 호출 테스트")
    void findUserByIdFailed() {
        //given, when, then
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.findUserById(1L));
    }

    @Test
    @DisplayName("id로 회원 정보 조회 실패 시 예외 호출 테스트")
    void findUserByloginIdFailed() {
        //given, when, then
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.findUser("loginId"));
    }

    @Test
    @DisplayName("회원 정보 수정 테스트")
    void modifyUser() {
        //given
        userService.addUser(UserSaveRequest.builder()
            .id(1L)
            .loginId("testId")
            .password("testPassword")
            .name("testName")
            .phoneNumber("01012345678")
            .build());

        UserPatchRequest userPatchRequest = UserPatchRequest.builder()
            .loginId("testId")
            .name("testName2")
            .phoneNumber("01012345679")
            .build();

        //when
        User modifyUser = userService.modifyUser(1L, userPatchRequest);

        //then
        assertThat(modifyUser.getName()).isEqualTo("testName2");
        assertThat(modifyUser.getPhoneNumber()).isEqualTo("01012345679");
    }
}
