package com.flab.CafeMap.web.user.dto;

import com.flab.CafeMap.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserGetResponse {

    private User user;

    public static UserGetResponse from(User user) {
        return UserGetResponse.builder()
            .user(user)
            .build();
    }
}
