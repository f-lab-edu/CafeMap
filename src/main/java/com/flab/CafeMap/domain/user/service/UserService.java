package com.flab.CafeMap.domain.user.service;

import com.flab.CafeMap.domain.user.exception.UserNotFoundException;
import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.dao.UserMapper;
import com.flab.CafeMap.domain.user.exception.DuplicatedUserIdException;
import com.flab.CafeMap.web.user.dto.UserPatchRequest;
import com.flab.CafeMap.web.user.dto.UserSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @RequiredArgsConstructor : final 필드에 대해 생성자 생성
 * @Transactional : 선언적 트랜잭션에 사용되는 애노테이션으로 테스트 이후 롤백
 */

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User addUser(UserSaveRequest userSaveRequest) {
        userMapper.selectUserByLoginId(userSaveRequest.getLoginId()).ifPresent(user -> {
            throw new DuplicatedUserIdException();});
        userSaveRequest.setPassword(passwordEncoder.encode(userSaveRequest.getPassword()));
        User user = userSaveRequest.toEntity();
        userMapper.insertUser(user);
        return user;
    }

    @Transactional(readOnly = true)
    public User findUser(String loginId) {
        return userMapper.selectUserByLoginId(loginId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
    }

    @Transactional(readOnly = true)
    public User findUserById(Long userId) {
        return userMapper.selectUserById(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
    }

    @Transactional
    public User modifyUser(Long userId, UserPatchRequest userPatchRequest) {
        User user = findUserById(userId);
        user.modify(userPatchRequest.getName(), userPatchRequest.getPhoneNumber(),
            user.getModifiedBy());
        userMapper.updateUser(user);
        return user;
    }
}
