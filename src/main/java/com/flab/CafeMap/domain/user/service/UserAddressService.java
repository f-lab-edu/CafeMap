package com.flab.CafeMap.domain.user.service;

import com.flab.CafeMap.domain.login.exception.UserNotFoundException;
import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.UserAddress;
import com.flab.CafeMap.domain.user.dao.UserAddressMapper;
import com.flab.CafeMap.domain.user.dao.UserMapper;
import com.flab.CafeMap.web.api.KakaoMapApi;
import com.flab.CafeMap.web.user.dto.UserAddressSaveRequest;
import com.flab.CafeMap.web.user.dto.kakao.KakaoMapApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @RequiredArgsConstructor : final 필드에 대해 생성자 생성
 * @Transactional : 선언적 트랜잭션에 사용되는 애노테이션으로 테스트 이후 롤백
 */

@Service
@RequiredArgsConstructor
public class UserAddressService {

    private final UserMapper userMapper;

    private final UserAddressMapper userAddressMapper;

    private final KakaoMapApi kakaoMapApi;

    @Transactional
    public UserAddress addUserAddress(Long id, UserAddressSaveRequest userAddressSaveRequest) {
        UserAddress userAddress = createUserAddress(id, userAddressSaveRequest);
        userAddressMapper.insertUserAddress(userAddress);
        return userAddress;
    }

    private UserAddress createUserAddress(Long id, UserAddressSaveRequest userAddressSaveRequest) {
        String loginId = findUserLoginId(id);


        return null;
    }

    private String findUserLoginId(Long id) {
        return userMapper.selectUserById(id).orElseThrow(() -> {
            throw new UserNotFoundException();
        }).getLoginId();
    }

}
