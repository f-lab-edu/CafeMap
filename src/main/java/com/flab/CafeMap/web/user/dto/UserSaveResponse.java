package com.flab.CafeMap.web.user.dto;

import com.flab.CafeMap.domain.user.User;
import lombok.Builder;
import lombok.Getter;

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
