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

@Transactional
@SpringBootTest
@ActiveProfiles("test")
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
