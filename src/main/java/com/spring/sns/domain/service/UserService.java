package com.spring.sns.domain.service;

import com.spring.sns.domain.dto.UserDTO;
import com.spring.sns.domain.dto.login.UserLoginRequestDTO;
import com.spring.sns.domain.model.entity.UserEntity;
import com.spring.sns.domain.repository.UserRepository;
import com.spring.sns.util.JwtTokenUtils;
import com.spring.sns.web.exception.AppBaseException;
import com.spring.sns.web.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.secret-key}")
    private String secretkey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;


    @Transactional
    public UserDTO join(String userName, String password) {
//        회원가입하는 userName으로 회원가입된 user가 있는지
        userRepository.findByUserName(userName)
                .ifPresent(user -> {
                    throw new AppBaseException(ErrorCode.DUPLICATED_USER_NAME, String.format("user name: %s is duplicated", userName));
                });

//        회원가입 진행 = user를 등록
        UserEntity userEntity = userRepository.save(UserEntity.of(userName, passwordEncoder.encode(password)));
        return UserDTO.fromEntity(userEntity);
    }

    public String login(String userName, String password) {
//        회원가입 여부 체크
        UserEntity userEntity = userRepository.findByUserName(userName).orElseThrow(() -> new AppBaseException(ErrorCode.USER_NOT_FOUND));

//        비밀번호 체크
        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new AppBaseException(ErrorCode.INVALID_PASSWORD);
        }

//        토큰 생성
        return JwtTokenUtils.generateToken(userName, secretkey, expiredTimeMs);
    }
}
