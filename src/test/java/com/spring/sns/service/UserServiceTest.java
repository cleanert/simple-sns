package com.spring.sns.service;

import com.spring.sns.domain.dto.request.UserJoinRequestDTO;
import com.spring.sns.domain.model.entity.UserEntity;
import com.spring.sns.domain.repository.UserRepository;
import com.spring.sns.domain.service.UserService;

import com.spring.sns.fixture.UserEntityFixture;
import com.spring.sns.web.exception.AppException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Test
    void 회원가입이_정상적으로_동작하는_경우() {
        String userName = "testUserName";
        String password = "testPassword";

//        mocking
        when(userRepository.findByUserName(userName)).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(Optional.of(UserEntityFixture.get(userName, password)));

//        어떠한 예외도 던지지 않는지
        Assertions.assertDoesNotThrow(() -> userService.join(userName, password));
    }

    @Test
    void 회원가입시_userName으로_회원가입을한_유저가_이미_있는경우() {
        String userName = "testUserName";
        String password = "testPassword";

        UserEntity fixture = UserEntityFixture.get(userName, password);

//        mocking
        when(userRepository.findByUserName(userName)).thenReturn(Optional.of(fixture));
        when(userRepository.save(any())).thenReturn(Optional.of(fixture));

        Assertions.assertThrows(AppException.class, () -> userService.join(userName, password));
    }

    @Test
    void 로그인이_정상적으로_동작하는_경우() {
        String userName = "testUserName";
        String password = "testPassword";

        UserEntity fixture = UserEntityFixture.get(userName, password);

//        mocking
        when(userRepository.findByUserName(userName)).thenReturn(Optional.of(fixture));

//        어떠한 예외도 던지지 않는지
        Assertions.assertDoesNotThrow(() -> userService.login(userName, password));
    }

    @Test
    void 로그인시_userName으로_회원가입한_유저가_없는_경우() {
        String userName = "testUserName";
        String password = "testPassword";

//        mocking
        when(userRepository.findByUserName(userName)).thenReturn(Optional.empty());

        Assertions.assertThrows(AppException.class, () -> userService.login(userName, password));
    }

    @Test
    void 로그인시_password_틀린경우() {
        String userName = "testUserName";
        String password = "testPassword";
        String wrongPassword = "testWrongPassword";

        UserEntity fixture = UserEntityFixture.get(userName, password);

//        mocking
        when(userRepository.findByUserName(userName)).thenReturn(Optional.of(fixture));

        Assertions.assertThrows(AppException.class, () -> userService.login(userName, wrongPassword));
    }
}
