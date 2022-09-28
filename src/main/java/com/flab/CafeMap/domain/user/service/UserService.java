package com.flab.CafeMap.domain.user.service;

import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.dao.UserMapper;
import com.flab.CafeMap.web.user.dto.UserSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //final 필드에 대해 생성자 생성

public class UserService {

    private final UserMapper userMapper;


    public User addUser(UserSaveRequest userSaveRequest) {
        if(userMapper.)
    }

    public User findUser(String loginId) {
        return;
    }
}
