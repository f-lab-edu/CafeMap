package com.flab.CafeMap.web.user.dto.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @Getter : getter 메소드를 자동으로 생성
 */

@Getter
public class SameName {

    @JsonProperty("region")
    private String[] region;

    @JsonProperty("keyword")
    private String keyword;

    @JsonProperty("selected_region")
    private String selectedRegion;
}
