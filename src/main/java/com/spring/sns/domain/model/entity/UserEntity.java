package com.spring.sns.domain.model.entity;

import com.spring.sns.domain.dto.request.UserJoinRequestDTO;
import com.spring.sns.domain.model.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "\"user\"")
@Getter @Setter
@SQLDelete(sql = "UPDATE \"user\" SET deleted_at = NOW() where id=?")
@Where(clause = "deleted_at is NULL")
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;


    @PrePersist
    void createdAt() {
        this.createdAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }

//    변환 메서드 (dto로 받은 데이터로 userEntity객체 생성) dto -> entity
//    public static UserEntity of(String userName, String password) {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUserName(userName);
//        userEntity.setPassword(password);
//
//        return userEntity;
//    }

    public static UserEntity of(UserJoinRequestDTO request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(request.getUserName());
        userEntity.setPassword(request.getPassword());

        return userEntity;
    }
}
