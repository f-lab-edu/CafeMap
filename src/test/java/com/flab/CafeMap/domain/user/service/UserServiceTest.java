package com.flab.CafeMap.domain.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.web.user.dto.UserSaveRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@Profile("test")
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("회원가입 테스트")
    void addUser() {
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

}
