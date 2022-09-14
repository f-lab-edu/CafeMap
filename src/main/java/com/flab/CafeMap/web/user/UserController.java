package com.flab.CafeMap.web.user;

import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.service.UserService;
import com.flab.CafeMap.web.user.dto.UserSaveRequest;
import com.flab.CafeMap.web.user.dto.UserSaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserSaveResponse> addUser(
        @Validated @RequestBody UserSaveRequest userSaveRequest) {

        User newUser = userService.addUser(userSaveRequest);
        return new ResponseEntity<>(UserSaveResponse.from(newUser),
            HttpStatus.CREATED);
    }
}
