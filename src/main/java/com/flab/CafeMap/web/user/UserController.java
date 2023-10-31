package com.flab.CafeMap.web.user;

import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.service.UserService;
import com.flab.CafeMap.web.user.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @RestController : @Controller + @ResponseBody
 * @RequiredArgsConstructor : final 필드에 대해 생성자 생성
 * @RequestMapping : 요청에 맞는 컨트롤러 매핑
 */

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
    public ResponseEntity<UserGetResponse> getUser(@PathVariable Long userId) {
        User user = userService.findUserById(userId);
        UserGetResponse response = UserGetResponse.from(user);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserPatchResponse> modifyUser(@PathVariable Long userId,
        @RequestBody UserPatchRequest userPatchRequest) {
        User user = userService.modifyUser(userId, userPatchRequest);
        return new ResponseEntity<>(UserPatchResponse.from(user), HttpStatus.OK);
    }
}
