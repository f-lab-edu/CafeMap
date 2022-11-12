package com.flab.CafeMap.web.interceptor;

import static com.flab.CafeMap.domain.login.service.LoginService.LOGIN_SESSION;

import com.flab.CafeMap.domain.login.exception.UnauthenticatedUserException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

        log.info("로그인 인증 체크 인터셉터 실행");

        HttpSession httpSession = request.getSession();

        if (httpSession == null || httpSession.getAttribute(LOGIN_SESSION) == null) {
            log.info("인증되지 않은 사용자 요청");
            throw new UnauthenticatedUserException();
        }
        return true;
    }
}
