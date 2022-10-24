package com.flab.CafeMap.domain.login.service;

import com.flab.CafeMap.domain.login.exception.DuplicateLoginSessionException;
import com.flab.CafeMap.domain.login.exception.InvalidPasswordException;
import com.flab.CafeMap.domain.login.exception.LoginIdNotFoundException;
import com.flab.CafeMap.domain.login.exception.NotFoundLoginSessionException;
import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.dao.UserMapper;
import com.flab.CafeMap.web.login.dto.LoginRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserMapper userMapper;

    public static final String LOGIN_SESSION = "loginId";

    public User login(LoginRequest loginRequest, @NotNull HttpSession session) {
        if (loginRequest.getLoginId().equals(session.getAttribute(LOGIN_SESSION))) {
            throw new DuplicateLoginSessionException();
        }

        User user = userMapper.selectUserByLoginId(loginRequest.getLoginId())
            .orElseThrow(() -> new LoginIdNotFoundException());

        if (loginRequest.getPassword().equals(user.getLoginId())) {
            session.setAttribute(LOGIN_SESSION, loginRequest.getLoginId());
        } else {
            throw new InvalidPasswordException();
        }
        return user;
    }

    public void logout(@NotNull HttpSession session) {
        if (session.getAttribute(LOGIN_SESSION) != null) {
            session.invalidate();
        } else {
            throw new NotFoundLoginSessionException();
        }
    }
}
