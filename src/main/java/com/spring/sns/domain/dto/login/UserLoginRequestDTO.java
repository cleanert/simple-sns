package com.spring.sns.domain.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginRequestDTO {

    private String userName;
    private String password;

}