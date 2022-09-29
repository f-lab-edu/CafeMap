package com.flab.CafeMap.web.user.dto;

import com.flab.CafeMap.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserGetResponse {

    private String loginId;
    private String name;
    private String phoneNumber;

    public static UserGetResponse from(User user) {
        return new UserGetResponse(user.getLoginId(), user.getName(), user.getPhoneNumber());
    }

}
