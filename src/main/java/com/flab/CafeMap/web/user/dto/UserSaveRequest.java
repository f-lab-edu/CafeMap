package com.flab.CafeMap.web.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveRequest {

    private String loginId;
    private String name;
    private String passsword;
    private String phoneNumber;
}
