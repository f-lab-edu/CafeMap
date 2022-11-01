package com.flab.CafeMap.web.user;

import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.service.UserService;
import com.flab.CafeMap.web.user.dto.UserGetResponse;
import com.flab.CafeMap.web.user.dto.UserPatchRequest;
import com.flab.CafeMap.web.user.dto.UserPatchResponse;
import com.flab.CafeMap.web.user.dto.UserSaveRequest;
import com.flab.CafeMap.web.user.dto.UserSaveResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<UserGetResponse> getUser(@PathVariable String userId) {
        User user = userService.findUser(userId);
        return new ResponseEntity<>(UserGetResponse.from(user), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<UserPatchResponse> modifyUser(
        @RequestBody UserPatchRequest userPatchRequest) {
        User user = userService.modifyUser(userPatchRequest);
        return new ResponseEntity<>(UserPatchResponse.from(user), HttpStatus.OK);
    }
}
