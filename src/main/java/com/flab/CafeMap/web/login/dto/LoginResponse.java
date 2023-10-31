package com.flab.CafeMap.web.login.dto;

import com.flab.CafeMap.domain.user.User;
import lombok.Builder;
import lombok.Getter;

/**
 *  @Getter : getter 메소드를 자동으로 생성
 *  @Builder : 빌더 패턴을 사용할 수 있게 도와주는 애노테이션
 */

@Getter
@Builder
public class LoginResponse {

    private String loginId;
    private String name;

    public static LoginResponse from(User user) {
        return LoginResponse.builder()
            .loginId(user.getLoginId())
            .name(user.getName())
            .build();
    }
}
