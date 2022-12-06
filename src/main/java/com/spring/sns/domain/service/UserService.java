package com.spring.sns.domain.service;

import com.spring.sns.domain.dto.UserDTO;
import com.spring.sns.domain.dto.request.UserJoinRequestDTO;
import com.spring.sns.domain.model.entity.UserEntity;
import com.spring.sns.domain.repository.UserRepository;
import com.spring.sns.web.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

//    public UserDTO join(String userName, String password) {
////        회원가입하는 userName으로 회원가입된 user가 있는지
//        userRepository.findByUserName(userName)
//                .ifPresent(user -> {
//                    throw new AppException();
//                });
//
////        회원가입 진행 = user를 등록
//        UserEntity userEntity = userRepository.save(UserEntity.of(userName, password));
//
//        return UserDTO.fromEntity(userEntity);
//    }

        public UserDTO join(UserJoinRequestDTO request) {
//        회원가입하는 userName으로 회원가입된 user가 있는지
        userRepository.findByUserName(request.getUserName())
                .ifPresent(user -> {
                    throw new AppException();
                });

//        회원가입 진행 = user를 등록
        UserEntity userEntity = userRepository.save(UserEntity.of(request));

        return UserDTO.fromEntity(userEntity);
    }

    public String login(String userName, String password) {
//        회원가입 여부 체크
        UserEntity userEntity = userRepository.findByUserName(userName).orElseThrow(AppException::new);

//        비밀번호 체크
        if (userEntity.getPassword().equals(password)) {
            throw new AppException();
        }

//        토큰 생성

        return "";
    }
}
