package com.spring.sns.domain.dto.signUp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinRequestDTO {

    private String userName;
    private String password;

}
