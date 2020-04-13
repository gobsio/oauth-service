package com.gobs.oauthservice.services.signup;

import javax.inject.Inject;

import com.gobs.oauthservice.domain.dtos.UserDTO;
import com.gobs.oauthservice.domain.entities.User;
import com.gobs.oauthservice.domain.exceptions.user.UserAlreadyRegisteredException;
import com.gobs.oauthservice.domain.mappers.users.UserMapper;
import com.gobs.oauthservice.domain.requests.UserRegistration;
import com.gobs.oauthservice.repositories.users.UsersRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Inject
    UsersRepository usersRepository;

    public UserDTO register(UserRegistration registration) throws Exception {
        User user = UserMapper.toEntity(registration);

        checkEmailAlreadyRegistered(user.getEmail());

        return UserMapper.toDto(usersRepository.save(user)); 
    }

    private boolean checkEmailAlreadyRegistered(String username) throws UserAlreadyRegisteredException {
        if (usersRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyRegisteredException("");
        }
        return true;
    }

}