package com.flab.CafeMap.web.user.dto.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Document {

    @JsonProperty("road_address")
    private RoadAddress roadAddress;

    private Address address;
}
