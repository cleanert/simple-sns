package com.spring.sns.web.controller;

import com.spring.sns.domain.dto.UserDTO;
import com.spring.sns.domain.dto.request.UserJoinRequestDTO;
import com.spring.sns.domain.dto.response.UserJoinResponseDTO;
import com.spring.sns.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public UserJoinResponseDTO join(@RequestBody UserJoinRequestDTO request) {
        UserDTO userDTO = userService.join(request.getUserName(), request.getPassword());

        return UserJoinResponseDTO.fromUserDTO(userDTO);
    }
}
