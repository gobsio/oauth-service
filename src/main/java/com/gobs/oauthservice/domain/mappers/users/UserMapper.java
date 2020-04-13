package com.gobs.oauthservice.domain.mappers.users;

import com.gobs.oauthservice.domain.dtos.UserDTO;
import com.gobs.oauthservice.domain.entities.User;
import com.gobs.oauthservice.domain.requests.UserRegistration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserMapper {

    public static UserDTO toDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .username(user.getEmail())
                .phone(user.getPhone())
                .active(user.getActive())
                .picture(user.getPicture())
            .build();
    }

    public static User toEntity(UserDTO user) {
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .username(user.getEmail())
                .phone(user.getPhone())
                .active(user.getActive())
                .picture(user.getPicture())
            .build();
    }

    public static User toEntity(UserRegistration registration) {
        return User.builder()
                .firstName(registration.getFirstName())
                .lastName(registration.getLastName())
                .email(registration.getEmail())
                .username(registration.getEmail())
                .phone(registration.getPhone())
                .password(new BCryptPasswordEncoder().encode(registration.getPassword()))
            .build();
    }

}
