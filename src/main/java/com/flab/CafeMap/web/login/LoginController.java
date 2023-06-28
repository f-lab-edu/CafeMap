package com.flab.CafeMap.web.login;

import com.flab.CafeMap.domain.login.service.LoginService;
import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.web.login.dto.LoginRequest;
import com.flab.CafeMap.web.login.dto.LoginResponse;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController : @Controller + @ResponseBody
 * @RequiredArgsConstructor : final 필드에 대해 생성자 생성
 */

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest loginRequest,
        HttpSession session) {
        User user = loginService.login(loginRequest, session);
        return new ResponseEntity<>(LoginResponse.from(user), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        loginService.logout(session);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
