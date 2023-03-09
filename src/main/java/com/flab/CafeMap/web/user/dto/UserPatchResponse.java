package com.flab.CafeMap.web.user.dto;

import com.flab.CafeMap.domain.user.User;
import java.time.LocalDateTime;
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
public class UserPatchResponse {

    private String loginId;
    private String name;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static UserPatchResponse from(User user) {
        return UserPatchResponse.builder()
                .loginId(user.getLoginId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .createdAt(user.getCreatedAt())
                .modifiedAt(user.getModifiedAt())
                .build();
    }
}
