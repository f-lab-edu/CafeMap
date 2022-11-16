package com.flab.CafeMap.web.user.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * @Getter : getter 메소드를 자동으로 생성
 * @Builder : 빌더 패턴을 사용할 수 있게 도와주는 애노테이션
 */

@Getter
@Builder
public class UserAddressSaveRequest {

    private String x;
    private String y;
    private String streetAddress;
    private String detailAddress;
}
