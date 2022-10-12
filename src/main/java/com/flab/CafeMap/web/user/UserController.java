package com.flab.CafeMap.web.user;

import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.service.UserService;
import com.flab.CafeMap.web.user.dto.UserGetResponse;
import com.flab.CafeMap.web.user.dto.UserSaveRequest;
import com.flab.CafeMap.web.user.dto.UserSaveResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserSaveResponse> addUser(
        @Validated @RequestBody UserSaveRequest userSaveRequest) {
        User newUser = userService.addUser(userSaveRequest);
        UserSaveResponse response = UserSaveResponse.from(newUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserGetResponse> getUser(@PathVariable String loginId) {
        User user = userService.findUser(loginId);
        UserGetResponse response = UserGetResponse.from(user);
        return ResponseEntity.ok(response);
    }
}
