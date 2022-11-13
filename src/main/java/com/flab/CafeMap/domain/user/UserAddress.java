package com.flab.CafeMap.domain.user;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @NoArgsConstructor : 인자가 없는 생성자를 만들어준다.
 * @AllArgsConstructor : 모든 인자가 있는 생성자를 만들어준다.
 */

@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {

    private Long id;
    private String loginId;
    private String streetAddress;
    private String detailAddress;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String createdBy;
    private String modifiedBy;
}
