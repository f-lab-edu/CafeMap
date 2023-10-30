package com.flab.CafeMap.web.cafe.dto;

import com.flab.CafeMap.domain.cafe.Cafe;
import lombok.Builder;
import lombok.Getter;

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
