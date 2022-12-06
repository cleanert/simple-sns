package com.spring.sns.web.controller;

import com.spring.sns.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public void join() {
        userService.join("", "");
    }
}
