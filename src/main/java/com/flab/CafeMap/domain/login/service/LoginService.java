package com.flab.CafeMap.domain.login.service;

import com.flab.CafeMap.domain.login.exception.DuplicatedLoginSessionException;
import com.flab.CafeMap.domain.login.exception.InvalidPasswordException;
import com.flab.CafeMap.domain.user.exception.UserNotFoundException;
import com.flab.CafeMap.domain.login.exception.LoginSessionNotFoundException;
import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.dao.UserMapper;
import com.flab.CafeMap.web.login.dto.LoginRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @RequiredArgsConstructor : final 필드에 대해 생성자 생성
 */

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    public static final String LOGIN_SESSION = "loginId";

    public User login(LoginRequest loginRequest, HttpSession session) {
        if (loginRequest.getLoginId().equals(session.getAttribute(LOGIN_SESSION))) {
            throw new DuplicatedLoginSessionException();
        }

        User user = userMapper.selectUserByLoginId(loginRequest.getLoginId())
            .orElseThrow(() -> new UserNotFoundException());

        if (isCheckPassword(loginRequest.getPassword(), user.getPassword())) {
            session.setAttribute(LOGIN_SESSION, loginRequest.getLoginId());
        } else {
            throw new InvalidPasswordException();
        }

        return user;
    }

    public void logout(HttpSession session) {
        if (session.getAttribute(LOGIN_SESSION) != null) {
            session.invalidate();
        } else {
            throw new LoginSessionNotFoundException();
        }
    }

    private boolean isCheckPassword(String password, String findPassword) {
        return passwordEncoder.matches(password, findPassword);
    }
}
