package com.flab.CafeMap.web.user.dto.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 *@Getter : getter 메소드를 자동으로 생성
 */

@Getter
public class Documents {

    @JsonProperty("id")
    private String id;

    @JsonProperty("place_name")
    private String placeName;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("category_group_code")
    private String categoryGroupCode;

    @JsonProperty("category_group_name")
    private String categoryGroupName;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("address_name")
    private String addressName;

    @JsonProperty("road_address_name")
    private String roadAddressName;

    @JsonProperty("x")
    private Double x;

    @JsonProperty("y")
    private Double y;

    @JsonProperty("place_url")
    private String placeUrl;

    @JsonProperty("distance")
    private String distance;
}
