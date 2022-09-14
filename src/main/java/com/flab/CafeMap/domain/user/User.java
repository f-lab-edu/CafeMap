package com.flab.CafeMap.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter //getter 메소드를 자동으로 생성해주는 애노테이션
@Setter //setter 메소드를 자동으로 생성해주는 애노테이션
@AllArgsConstructor //모든 인자가 존재하는 생성자를 만들어주는 애노테이션
public class User {

    private Long id;
    private String loginId;
    private String name;
    private String password;
    private String phoneNumber;
    private String createdAt;
    private String modifiedAt;

}
