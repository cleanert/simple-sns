package com.spring.sns.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.sns.domain.dto.request.UserLoginRequestDTO;
import com.spring.sns.domain.dto.request.UserJoinRequestDTO;
import com.spring.sns.domain.entity.User;
import com.spring.sns.domain.service.UserService;
import com.spring.sns.web.exception.AppException;
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
public class UserControllerTest {

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

        when(userService.join()).thenReturn(mock(User.class));

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        // TODO : add request body
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

        when(userService.join()).thenThrow(new AppException());

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        // TODO : add request body
                        .content(objectMapper.writeValueAsBytes(
                                new UserJoinRequestDTO(userName, password)
                        ))
                ).andDo(print())
                .andExpect(status().isConflict());
    }
}
