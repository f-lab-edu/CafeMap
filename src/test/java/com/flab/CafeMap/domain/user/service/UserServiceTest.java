package com.flab.CafeMap.domain.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.web.user.dto.UserSaveRequest;
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
            .Id(1L)
            .loginId("testId")
            .name("testName")
            .password("testPassword")
            .phoneNumber("01012345678")
            .build());

        //when
        User user = userService.findUser(1L);

        //then
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getName()).isEqualTo("testName");
    }

}
