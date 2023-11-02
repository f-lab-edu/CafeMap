package com.flab.CafeMap.web.user.dto.kakao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @Getter : getter 메소드를 자동으로 생성
 * @Builder : 빌더 패턴을 사용할 수 있게 도와주는 애노테이션
 * @AllArgsConstructor  : 모든 필드를 받는 생성자를 만들어준다.
 */

@Getter
@Builder
@AllArgsConstructor
public class KakaoMapApiRequest {

    private String x;
    private String y;
}
