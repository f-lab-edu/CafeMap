package com.flab.CafeMap.domain.user.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.flab.CafeMap.domain.user.User;
import java.time.LocalDateTime;
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
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("User insert 쿼리 테스트")
    void insertUser() {
        //given
        User user1 = createUser("id", "testName", "testPassword", "01012345678", "testCreatedBy");
        User user2 = createUser("id2", "testName2", "testPassword2", "01012345679",
            "testCreatedBy2");

        //when
        int count = 0;
        count += userMapper.insertUser(user1);
        count += userMapper.insertUser(user2);

        //then
        assertThat(count).isEqualTo(2);
    }

    @Test
    @DisplayName("loginId로 User 정상 반환 확인 테스트")
    void selectUserByLoginId() {
        //given
        User user = createUser("id", "testName", "testPassword", "01012345678", "testCreatedBy");
        userMapper.insertUser(user);

        //when
        User findUser = (userMapper.selectUserByLoginId(user.getLoginId())).orElseThrow();

        //then
        assertThat(findUser.getLoginId()).isEqualTo(user.getLoginId());
        assertThat(findUser.getName()).isEqualTo(user.getName());
    }

    @Test
    @DisplayName("id로 User 정상 반환 확인 테스트")
    void selectUserById() {
        //given
        User user1 = createUser("id", "testName", "testPassword", "01012345678", "testCreatedBy");
        User user2 = createUser("id2", "testName2", "testPassword2", "01012345679",
            "testCreatedBy2");
        userMapper.insertUser(user1);
        userMapper.insertUser(user2);

        //when
        User findUser1 = (userMapper.selectUserById(user1.getId())).orElseThrow();
        User findUser2 = (userMapper.selectUserById(user2.getId())).orElseThrow();

        //then
        assertThat(findUser1.getId()).isEqualTo(user1.getId());
        assertThat(findUser2.getId()).isEqualTo(user2.getId());
    }

    @Test
    @DisplayName("User update 쿼리 테스트")
    void updateUser() {
        //given
        User user1 = createUser("id", "testName", "testPassword", "01012345678",
            "testCreatedBy");
        userMapper.insertUser(user1);
        User user = userMapper.selectUserById(user1.getId()).orElseThrow();
        user.modify("testName2", "01012345679", "testModifiedBy");

        //when
        int count = 0;
        count += userMapper.updateUser(user);

        //then
        assertThat(count).isEqualTo(1);
        assertThat(user.getName()).isEqualTo("testName2");
        assertThat(user.getPhoneNumber()).isEqualTo("01012345679");
    }

    private User createUser(String testId, String testName, String testPassword,
        String testPhoneNumber, String testCreatedBy) {
        return User.builder()
            .loginId(testId)
            .name(testName)
            .password(testPassword)
            .phoneNumber(testPhoneNumber)
            .createdBy(testCreatedBy).createdAt(LocalDateTime.now())
            .build();
    }
}
