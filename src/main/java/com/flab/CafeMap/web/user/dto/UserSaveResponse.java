package com.flab.CafeMap.web.user.dto;

import com.flab.CafeMap.domain.user.User;
import lombok.Builder;
import lombok.Getter;

/**
 * @Getter : getter 메소드를 자동으로 생성
 * @Builder : 빌더 패턴을 사용할 수 있게 도와주는 애노테이션
 */

@Getter
@Builder
public class UserSaveResponse {

    private String loginId;
    private String name;
    private String phoneNumber;

    public static UserSaveResponse from(User user) {
        return UserSaveResponse.builder()
            .loginId(user.getLoginId())
            .name(user.getName())
            .phoneNumber(user.getPhoneNumber())
            .build();
    }
}
