package com.flab.CafeMap.web.user.dto.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @Getter : getter 메소드를 자동으로 생성
 */

@Getter
public class Address {

    @JsonProperty("address_name")
    private String addressName;

    @JsonProperty("region_1depth_name")
    private String region1DepthName;

    @JsonProperty("region_2depth_name")
    private String region2DepthName;

    @JsonProperty("region_3depth_name")
    private String region3DepthName;

    @JsonProperty("mountain_yn")
    private String mountainYn;

    @JsonProperty("main_address_no")
    private String mainAddressNo;

    @JsonProperty("sub_address_no")
    private String subAddressNo;
}
