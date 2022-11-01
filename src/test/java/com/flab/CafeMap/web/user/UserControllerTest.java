package com.flab.CafeMap.web.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.service.UserService;
import com.flab.CafeMap.web.user.dto.UserPatchRequest;
import com.flab.CafeMap.web.user.dto.UserSaveRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * @SpringBootTest : SpringBoot 통합테스트에 사용되는 애노테이션으로 @SpringBootApplication을 찾아가 하위의 모든 빈을 스캔한다.
 * @AutoConfigureMockMvc : 컨트롤러를 테스트 할 때 서블릿 컨테이너에 대한 mock 객체 생성
 * @ActiveProfiles : 스프링 컨테이터 실행 시 실행 환경을 지정해주는 애노테이션으로, 테스트 시 특정 빈을 로드
 * @Transactional : 선언적 트랜잭션에 사용되는 애노테이션으로 테스트 이후 롤백
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class UserControllerTest {

    @Mock
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("회원가입 성공 시 201 상태코드 반환")
    void addUser() throws Exception {
        //given
        UserSaveRequest userSaveRequest = createUser();

        //when
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userSaveRequest))
                .accept(MediaType.APPLICATION_JSON))

            //then
            .andDo(print()).andExpect(status().isCreated())
            .andExpect(jsonPath("$.loginId").value(userSaveRequest.getLoginId()))
            .andExpect(jsonPath("$.name").value(userSaveRequest.getName()))
            .andExpect(jsonPath("$.phoneNumber").value(userSaveRequest.getPhoneNumber()));
    }

    @Test
    @DisplayName("회원가입 시 필수 값이 누락되었을 때 400 상태코드 반환")
    void addUserFail() throws Exception {
        //given
        UserSaveRequest userSaveRequest = UserSaveRequest.builder().loginId("testAddUserFail")
            .name("testName").password("testPassword").build();

        //when
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userSaveRequest))
                .accept(MediaType.APPLICATION_JSON))

            //then
            .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("회원 아이디로 회원 정보 조회 테스트")
    void getUser() throws Exception {
        //given
        UserSaveRequest userSaveRequest = createUser();
        User user = userService.addUser(userSaveRequest);

        //when

    }

    @Test
    @DisplayName("회원 정보 수정 테스트")
    void modifyUser() throws Exception {
        //given
        UserSaveRequest userSaveRequest = createUser();

        mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userSaveRequest))
            .accept(MediaType.APPLICATION_JSON));

        //when
        UserPatchRequest userPatchRequest = UserPatchRequest.builder()
            .loginId(userSaveRequest.getLoginId())
            .name("testName2")
            .phoneNumber("01012345679")
            .build();

        mockMvc.perform(patch("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userPatchRequest))
                .accept(MediaType.APPLICATION_JSON))
            //then
            .andDo(print()).andExpect(jsonPath("$.loginId").value(userSaveRequest.getLoginId()))
            .andExpect(jsonPath("$.name").value(userPatchRequest.getName()))
            .andExpect(jsonPath("$.phoneNumber").value(userPatchRequest.getPhoneNumber()));
    }

    private UserSaveRequest createUser() {
        return UserSaveRequest.builder()
            .loginId("userControllerTestId")
            .name("testName")
            .password("testPassword")
            .phoneNumber("01012345678")
            .build();
    }
}

