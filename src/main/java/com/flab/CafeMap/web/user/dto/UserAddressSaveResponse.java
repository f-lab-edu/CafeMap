package com.flab.CafeMap.web.user.dto;

import com.flab.CafeMap.domain.user.UserAddress;
import lombok.Builder;
import lombok.Getter;

/**
 * @Getter : getter 메소드를 자동으로 생성
 * @Builder : 빌더 패턴을 사용할 수 있게 도와주는 애노테이션
 */

@Getter
@Builder
public class UserAddressSaveResponse {

    private String loginId;
    private String streetAddress;
    private String detailAddress;
    private String longitude;
    private String latitude;

    public static UserAddressSaveResponse from(UserAddress userAddress) {
        return UserAddressSaveResponse.builder()
            .loginId(userAddress.getLoginId())
            .streetAddress(userAddress.getStreetAddress())
            .detailAddress(userAddress.getDetailAddress())
            .longitude(userAddress.getLongitude())
            .latitude(userAddress.getLatitude())
            .build();
    }
}
