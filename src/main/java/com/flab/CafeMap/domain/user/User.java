package com.flab.CafeMap.domain.user;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter //getter 메소드를 자동으로 생성
@Setter //setter 메소드를 자동으로 생성
@NoArgsConstructor //인자가 없는 생성자를 만들어준다.
@AllArgsConstructor //모든 인자가 존재하는 생성자를 만들어준다.
public class User {

    private Long id;
    private String loginId;
    private String name;
    private String password;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
