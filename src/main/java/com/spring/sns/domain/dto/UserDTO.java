package com.spring.sns.domain.dto;

import com.spring.sns.domain.model.UserRole;
import com.spring.sns.domain.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String userName;
    private String password;
    private UserRole userRole;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;


//    변환 메서드 entity -> dto
    public static UserDTO fromEntity(UserEntity userEntity) {
        return new UserDTO(
                userEntity.getId(),
                userEntity.getUserName(),
                userEntity.getPassword(),
                userEntity.getRole(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt(),
                userEntity.getDeletedAt()
        );
    }
}
