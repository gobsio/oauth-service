package com.gobs.oauthservice.domain.mappers.users;

import com.gobs.oauthservice.domain.dtos.UserDTO;
import com.gobs.oauthservice.domain.entities.User;

public class UserMapper {

    public static UserDTO toDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
            .build();
    }

    public static User toEntity(UserDTO user) {
        return User.builder()
                .id(user.getId())
            .build();
    }

}