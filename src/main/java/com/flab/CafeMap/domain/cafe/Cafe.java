package com.flab.CafeMap.domain.cafe;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Getter : getter 메소드를 자동으로 생성
 * @NoArgsConstructor : 필드가 없는 기본 생성자를 만들어준다.
 * @AllArgsConstructor  : 모든 필드를 받는 생성자를 만들어준다.
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cafe {

    private Long id;
    private String cafeId;
    private String name;
    private String latitude;
    private String longitude;
    private String userLoginId;
    private LocalDateTime createdAt;
}
