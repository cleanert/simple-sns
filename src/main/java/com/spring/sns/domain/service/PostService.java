package com.spring.sns.domain.service;


import com.spring.sns.domain.model.entity.PostEntity;
import com.spring.sns.domain.model.entity.UserEntity;
import com.spring.sns.domain.repository.PostRepository;
import com.spring.sns.domain.repository.UserRepository;
import com.spring.sns.web.exception.AppBaseException;
import com.spring.sns.web.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Transactional
    public void create (String title, String body, String userName) {
//        유저를 찾고
        UserEntity user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppBaseException(ErrorCode.USER_NOT_FOUND));

//        포스트 저장
        PostEntity saved = postRepository.save(PostEntity.builder()
                .title(title)
                .body(body)
                .user(user).build());

//        return
    }
}
