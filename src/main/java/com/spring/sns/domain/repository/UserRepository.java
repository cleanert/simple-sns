package com.spring.sns.domain.repository;

import com.spring.sns.domain.model.User;
import com.spring.sns.domain.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity>findByUserName(String userName);
}
