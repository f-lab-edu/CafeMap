package com.flab.CafeMap.domain.user;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Getter : getter 메소드를 자동으로 생성
 * @Builder : 빌더 패턴을 사용할 수 있게 도와주는 애노테이션
 * @NoArgsConstructor : 필드가 없는 기본 생성자를 만들어준다.
 * @AllArgsConstructor  : 모든 필드를 받는 생성자를 만들어준다.
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {

    private Long id;
    private String loginId;
    private String streetAddress;
    private String detailAddress;
    private Double latitude;
    private Double longitude;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String createdBy;
    private String modifiedBy;
}
