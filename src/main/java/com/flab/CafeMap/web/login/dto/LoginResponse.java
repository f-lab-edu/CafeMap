package com.flab.CafeMap.web.login.dto;

import com.flab.CafeMap.domain.user.User;
import lombok.Builder;
import lombok.Getter;

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
