package com.spring.sns.domain.service;

import com.spring.sns.domain.model.User;
import com.spring.sns.domain.model.entity.UserEntity;
import com.spring.sns.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User join(String userName, String password) {
//        회원가입하는 userName으로 회원가입된 user가 있는지
        Optional<UserEntity> userEntity = userRepository.findByUserName(userName);

//        회원가입 진행 = user를 등록
        userRepository.save(new UserEntity());

        return new User();
    }

    public String login() {
        return "";
    }
}
