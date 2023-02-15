package com.flab.CafeMap.web.cafe.dto;

import com.flab.CafeMap.domain.cafe.Cafe;
import lombok.Builder;
import lombok.Getter;

/**
 * @Getter : getter 메소드를 자동으로 생성
 * @Builder : 빌더 패턴을 사용할 수 있게 도와주는 애노테이션
 */

@Getter
@Builder
public class CafeSaveResponse {

    private Cafe cafe;

    public static CafeSaveResponse from(Cafe cafe) {
        return CafeSaveResponse.builder()
            .cafe(cafe)
            .build();
    }
}
