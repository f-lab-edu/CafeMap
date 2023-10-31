package com.flab.CafeMap.web.user.dto.kakao;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Getter : getter 메소드를 자동으로 생성
 * @NoArgsConstructor : 인자가 없는 생성자를 만들어준다.
 */

@Getter
@NoArgsConstructor
public class KakaoMapApiResponse {

    private Meta meta;
    private List<Documents> document;
}
