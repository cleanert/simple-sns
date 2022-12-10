package com.spring.sns.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.sns.domain.dto.login.UserLoginRequestDTO;
import com.spring.sns.domain.dto.signUp.UserJoinRequestDTO;
import com.spring.sns.domain.dto.UserDTO;
import com.spring.sns.domain.service.UserService;
import com.spring.sns.web.exception.AppBaseException;
import com.spring.sns.web.exception.ErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserDTOControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;


    @Test
    public void 회원가입() throws Exception {
        String userName = "testUserName";
        String password = "testPassword";
        UserJoinRequestDTO request = new UserJoinRequestDTO(userName, password);

        when(userService.join(userName, password)).thenReturn(mock(UserDTO.class));

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(
                                new UserJoinRequestDTO(userName, password)
                        ))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 회원가입시_이미_회원가입된_userName으로_회원가입을_하는경우_에러반환() throws Exception {
        String userName = "testUserName";
        String password = "testPassword";
        UserJoinRequestDTO request = new UserJoinRequestDTO(userName, password);

        when(userService.join(userName, password)).thenThrow(new AppBaseException(ErrorCode.DUPLICATED_USER_NAME, ""));

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(
                                new UserJoinRequestDTO(userName, password)
                        ))
                ).andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void 로그인() throws Exception {
        String userName = "testUserName";
        String password = "testPassword";
        UserLoginRequestDTO request = new UserLoginRequestDTO(userName, password);

        when(userService.login(userName, password)).thenReturn("test_token");

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(
                                new UserLoginRequestDTO(userName, password)
                        ))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 로그인시_회원가입이_안된_userName을_입력할경우_에러반환() throws Exception {
        String userName = "testUserName";
        String password = "testPassword";
        UserLoginRequestDTO request = new UserLoginRequestDTO(userName, password);

        when(userService.login(userName, password)).thenThrow(new AppBaseException(ErrorCode.USER_NOT_FOUND));

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(
                                new UserLoginRequestDTO(userName, password)
                        ))
                ).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void 로그인시_틀린_password를_입력할_경우_에러반환() throws Exception {
        String userName = "testUserName";
        String password = "testPassword";
        UserLoginRequestDTO request = new UserLoginRequestDTO(userName, password);

        when(userService.login(userName, password)).thenThrow(new AppBaseException(ErrorCode.INVALID_PASSWORD ));

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(
                                new UserLoginRequestDTO(userName, password)
                        ))
                ).andDo(print())
                .andExpect(status().isUnauthorized());
    }
}
