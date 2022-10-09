package com.flab.CafeMap.web.user.dto;

import com.flab.CafeMap.domain.user.User;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserSaveRequest {

    @NotBlank
    private String loginId;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    private String phoneNumber;

    public User toEntity() {
        return User.builder()
            .loginId(this.loginId)
            .name(this.name)
            .password(this.password)
            .phoneNumber(this.phoneNumber)
            .createdBy(this.getName())
            .build();
    }
}
