package com.flab.CafeMap.web.cafe.dto;

import com.flab.CafeMap.domain.cafe.Cafe;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Getter : getter 메소드를 자동으로 생성
 * @Builder : 빌더 패턴을 사용할 수 있게 도와주는 애노테이션
 * @NoArgsConstructor : 인자가 없는 생성자를 만들어준다.
 * @AllArgsConstructor : 모든 필드를 받는 생성자를 만들어준다.
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CafeSaveRequest {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    public Cafe toEntity() {
        return Cafe.builder()
            .id(this.id)
            .name(this.name)
            .latitude(this.latitude)
            .longitude(this.longitude)
            .build();
    }
}
