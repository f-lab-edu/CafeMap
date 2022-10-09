package com.flab.CafeMap.domain.user.service;

import com.flab.CafeMap.domain.login.exception.LoginIdNotFoundException;
import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.dao.UserMapper;
import com.flab.CafeMap.web.user.dto.UserGetResponse;
import com.flab.CafeMap.web.user.dto.UserSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor //final 필드에 대해 생성자 생성
@Transactional
public class UserService {

    private final UserMapper userMapper;

    public User addUser(UserSaveRequest userSaveRequest) {
        User user = userSaveRequest.toEntity();
        userMapper.insertUser(user);
        return user;
    }

    @Transactional(readOnly = true)
    public User findUser(String loginId) {
        return userMapper.selectUserByLoginId(loginId).orElseThrow(() -> {
            throw new LoginIdNotFoundException();
        });
    }

}
