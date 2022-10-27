package com.flab.CafeMap.domain.login.service;

import com.flab.CafeMap.domain.login.exception.DuplicatedLoginSessionException;
import com.flab.CafeMap.domain.login.exception.LoginIdNotFoundException;
import com.flab.CafeMap.domain.login.exception.LoginSessionNotFoundException;
import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.dao.UserMapper;
import com.flab.CafeMap.web.login.dto.LoginRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @RequiredArgsConstructor : final 필드에 대해 생성자 생성
 */

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserMapper userMapper;
    public static final String LOGIN_SESSION = "loginId";

    public User login(LoginRequest loginRequest, @NotNull HttpSession session) {
        if (loginRequest.getLoginId().equals(session.getAttribute(LOGIN_SESSION))) {
            throw new DuplicatedLoginSessionException();
        }

        User user = userMapper.selectUserByLoginId(loginRequest.getLoginId())
            .orElseThrow(() -> new LoginIdNotFoundException());

        session.setAttribute(LOGIN_SESSION, user.getLoginId());
        return user;
    }

    public void logout(@NotNull HttpSession session) {
        if (session.getAttribute(LOGIN_SESSION) != null) {
            session.invalidate();
        } else {
            throw new LoginSessionNotFoundException();
        }
    }
}
