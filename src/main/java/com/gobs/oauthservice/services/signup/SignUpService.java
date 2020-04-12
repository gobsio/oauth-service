package com.gobs.oauthservice.services.signup;

import com.gobs.oauthservice.domain.dtos.UserDTO;
import com.gobs.oauthservice.domain.entities.User;
import com.gobs.oauthservice.domain.mappers.users.UserMapper;

import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    public UserDTO register(UserDTO userDTO) throws Exception {
        // transform DTO to Entity
        User user = UserMapper.toEntity(userDTO);

        // validating...

        // saving...
        
        return UserMapper.toDto(user); // transform to dto.
    }

}