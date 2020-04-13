package com.gobs.oauthservice.services.signup;

import javax.inject.Inject;

import com.gobs.oauthservice.domain.dtos.UserDTO;
import com.gobs.oauthservice.domain.entities.User;
import com.gobs.oauthservice.domain.mappers.users.UserMapper;
import com.gobs.oauthservice.domain.requests.UserRegistration;
import com.gobs.oauthservice.repositories.users.UsersRepository;

import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Inject
    UsersRepository usersRepository;

    public UserDTO register(UserRegistration registration) throws Exception {
        User user = UserMapper.toEntity(registration);

        validate(user);

        return UserMapper.toDto(usersRepository.save(user)); 
    }

    private boolean validate(User user) throws Exception {
        if (user.getFirstName() == null || user.getFirstName().equals("")) {
            throw new IllegalArgumentException("");
        }
        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new IllegalArgumentException("");
        }
        if (user.getPhone() == null || user.getPhone().equals("")) {
            throw new IllegalArgumentException("");
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            throw new IllegalArgumentException("");
        }
        return true;
    }

}