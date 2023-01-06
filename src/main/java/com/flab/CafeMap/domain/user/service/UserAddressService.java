package com.flab.CafeMap.domain.user.service;

import com.flab.CafeMap.domain.login.exception.UserNotFoundException;
import com.flab.CafeMap.domain.user.UserAddress;
import com.flab.CafeMap.domain.user.dao.UserAddressMapper;
import com.flab.CafeMap.domain.user.dao.UserMapper;
import com.flab.CafeMap.web.api.KakaoMapApi;
import com.flab.CafeMap.web.user.dto.UserAddressSaveRequest;
import com.flab.CafeMap.web.user.dto.kakao.KakaoMapApiRequest;
import com.flab.CafeMap.web.user.dto.kakao.KakaoMapApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @RequiredArgsConstructor : final 필드에 대해 생성자 생성
 * @Transactional : 선언적 트랜잭션에 사용되는 애노테이션으로 테스트 이후 롤백
 */

@Service
@RequiredArgsConstructor
public class UserAddressService {

    private final UserService userService;
    private final UserMapper userMapper;

    private final UserAddressMapper userAddressMapper;

    private final KakaoMapApi kakaoMapApi;

    @Transactional
    public UserAddress addUserAddress(Long userId, UserAddressSaveRequest userAddressSaveRequest, KakaoMapApiRequest kakaoMapApiRequest) {

        userService.findUserById(userId);

        KakaoMapApiResponse kakaoMapApiResponse = kakaoMapApi.getAddressByCoordinates(kakaoMapApiRequest).getBody();

        UserAddress userAddress = UserAddress.builder()
            .id(userId)
            .streetAddress(userAddressSaveRequest.getStreetAddress())
            .detailAddress(userAddressSaveRequest.getDetailAddress())
            .latitude(kakaoMapApiResponse.getDocuments().get(0).getX())
            .longitude(kakaoMapApiResponse.getDocuments().get(0).getY())
            .build();

        userAddressMapper.insertUserAddress(userAddress);
        return userAddress;
    }

    @Transactional
    public String findUserLoginId(Long id) {
        return userMapper.selectUserById(id).orElseThrow(() -> {
            throw new UserNotFoundException();
        }).getLoginId();
    }
}