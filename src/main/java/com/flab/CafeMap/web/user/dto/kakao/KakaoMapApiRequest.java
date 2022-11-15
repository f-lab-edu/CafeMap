package com.flab.CafeMap.web.user.dto.kakao;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoMapApiRequest {

    private String category_group_code = "CE7";
    private String x;
    private String y;
    private Integer radius;
}
