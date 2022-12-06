package com.spring.sns.domain.service;


import com.spring.sns.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User join() {
        return new User();
    }
}
