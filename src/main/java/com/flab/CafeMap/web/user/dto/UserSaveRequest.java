package com.flab.CafeMap.web.user.dto;

import com.flab.CafeMap.domain.user.User;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveRequest {

    @NotBlank
    private String loginId;

    @NotBlank
    private String name;

    @NotBlank
    private String passsword;

    @NotBlank
    private String phoneNumber;

    public User toEntity() {
        return User.builder()
            .loginId(this.loginId)
            .name(this.name)
            .password(this.passsword)
            .phoneNumber(this.phoneNumber)
            .build();
    }
}
