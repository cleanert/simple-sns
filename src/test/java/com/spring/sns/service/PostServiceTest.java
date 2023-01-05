package com.spring.sns.service;


import com.spring.sns.domain.model.entity.PostEntity;
import com.spring.sns.domain.model.entity.UserEntity;
import com.spring.sns.domain.repository.PostRepository;
import com.spring.sns.domain.repository.UserRepository;
import com.spring.sns.domain.service.PostService;
import com.spring.sns.web.exception.AppBaseException;
import com.spring.sns.web.exception.ErrorCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PostRepository postRepository;

    @Test
    void 포스트작성이_성공한_경우() {
        String title = "title1";
        String body = "body1";
        String userName = "userName1";

        when(userRepository.findByUserName(userName)).thenReturn(Optional.of(Mockito.mock(UserEntity.class)));
        when(postRepository.save(ArgumentMatchers.any())).thenReturn(Mockito.mock(PostEntity.class));

        Assertions.assertDoesNotThrow(() -> postService.create(title, body, userName));
    }

    @Test
    void 포스트작성시_요쳥한_유저가_존재하지않는_경우() {
        String title = "title1";
        String body = "body1";
        String userName = "userName1";

        when(userRepository.findByUserName(userName)).thenReturn(Optional.empty());
        when(postRepository.save(ArgumentMatchers.any())).thenReturn(Mockito.mock(PostEntity.class));

        AppBaseException e = Assertions.assertThrows(AppBaseException.class, () -> postService.create(title, body, userName));
        assertEquals(ErrorCode.USER_NOT_FOUND, e.getErrorCode());
    }
}
