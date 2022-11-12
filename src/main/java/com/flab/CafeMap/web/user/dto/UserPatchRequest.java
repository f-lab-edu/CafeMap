package com.flab.CafeMap.web.user.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Getter : getter 메소드를 자동으로 생성
 * @Builder : 빌더 패턴을 사용할 수 있게 도와주는 애노테이션
 * @NoArgsConstructor : 인자가 없는 생성자를 만들어준다.
 * @AllArgsConstructor : 빌더 패턴을 사용할 수 있게 도와주는 애노테이션
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPatchRequest {

    @NotNull
    private Long id;

    @NotNull
    private String loginId;

    @NotNull
    private String name;

    @NotNull
    private String phoneNumber;
}
