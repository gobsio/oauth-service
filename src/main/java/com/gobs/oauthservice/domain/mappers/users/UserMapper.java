package com.gobs.oauthservice.domain.mappers.users;

import com.gobs.oauthservice.domain.dtos.UserDTO;
import com.gobs.oauthservice.domain.entities.User;
import com.gobs.oauthservice.domain.requests.UserRegistration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    public static User toEntity(UserRegistration registration) {
        return User.builder()
                .firstName(registration.getEmail())
                .lastName(registration.getEmail())
                .email(registration.getEmail())
                .username(registration.getEmail())
                .phone(registration.getEmail())
                .password(new BCryptPasswordEncoder().encode(registration.getPassword()))
            .build();
    }

}
