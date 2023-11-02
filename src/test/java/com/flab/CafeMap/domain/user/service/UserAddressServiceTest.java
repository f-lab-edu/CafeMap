package com.flab.CafeMap.domain.user.service;

import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.UserAddress;
import com.flab.CafeMap.domain.user.dao.UserAddressMapper;
import com.flab.CafeMap.domain.user.exception.UserNotFoundException;
import com.flab.CafeMap.web.user.dto.UserAddressSaveRequest;
import com.flab.CafeMap.web.user.dto.UserSaveRequest;
import com.flab.CafeMap.web.user.dto.kakao.KakaoMapApiRequest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @SpringBootTest : SpringBoot 통합테스트에 사용되는 애노테이션으로 @SpringBootApplication을 찾아가 하위의 모든 빈을 스캔한다.
 * @ActiveProfiles : 스프링 컨테이터 실행 시 실행 환경을 지정해주는 애노테이션으로, 테스트 시 특정 빈을 로드
 * @Transactional : 선언적 트랜잭션에 사용되는 애노테이션으로 테스트 이후 롤백
 */

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UserAddressServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserAddressMapper userAddressMapper;

    @Autowired
    UserAddressService userAddressService;

    @Test
    @DisplayName("회원 정보와 주소 등록 테스트")
    @Disabled
    void addUserAddressTest() {
        //given
        UserSaveRequest userSaveRequest = createUser();
        UserAddressSaveRequest userAddressSaveRequest = createUserAddress();
        KakaoMapApiRequest kakaoMapApiRequest = KakaoMapApiRequest.builder()
                .x("127.031516177")
                .y("37.514589514733")
                .build();
        User user = userService.addUser(userSaveRequest);

        //when
        UserAddress userAddress = userAddressService.addUserAddress(1L, kakaoMapApiRequest, userAddressSaveRequest);

        //then
        assertThat(userAddress.getLatitude()).isNotNull();
        assertThat(userAddress.getLongitude()).isNotNull();
    }

    @Test
    @DisplayName("회원 주소를 찾을 수 없는 경우 UserAddressNotFoundException 발생")
    void findUserAddressByUserIdNotFound() {
        String loginId = "loginId";

        assertThrows(UserNotFoundException.class, () -> {
            userAddressService.findUserAddressByUserLoginId(loginId);
        });
    }

    private UserSaveRequest createUser() {
        return UserSaveRequest.builder()
                .loginId("testId")
                .name("testName")
                .password("1234")
                .phoneNumber("01012345678")
                .build();
    }

    private UserAddressSaveRequest createUserAddress() {
        return UserAddressSaveRequest.builder()
                .streetAddress("서울시 강남구")
                .detailAddress("논현동 207")
                .build();
    }
}