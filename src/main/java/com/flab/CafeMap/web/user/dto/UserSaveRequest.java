package com.flab.CafeMap.web.user.dto;

import com.flab.CafeMap.domain.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Getter : getter 메소드를 자동으로 생성
 * @Builder : 빌더 패턴을 사용할 수 있게 도와주는 애노테이션
 * @NoArgsConstructor : 인자가 없는 생성자를 만들어준다.
 * @AllArgsConstructor : 모든 필드를 받는 생성자를 만들어준다.
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveRequest {

    @NotNull
    private Long id;

    @NotNull
    private String loginId;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String phoneNumber;

    public void setPassword(String password) {
        this.password = password;
    }

    public User toEntity() {
        return User.builder().id(this.id).loginId(this.loginId).name(this.name).password(this.password).phoneNumber(this.phoneNumber).createdBy(this.name).build();
    }
}
