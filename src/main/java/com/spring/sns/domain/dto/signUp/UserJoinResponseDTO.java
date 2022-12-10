package com.spring.sns.domain.dto.signUp;

import com.spring.sns.domain.dto.UserDTO;
import com.spring.sns.domain.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinResponseDTO {

    private Long id;
    private String userName;
    private UserRole userRole;

    public static UserJoinResponseDTO fromUserDTO(UserDTO userDTO) {
        return new UserJoinResponseDTO(
                userDTO.getId(),
                userDTO.getUserName(),
                userDTO.getUserRole()
        );
    }
}
