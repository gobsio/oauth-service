package com.gobs.oauthservice.services.signup;

import com.gobs.oauthservice.domain.entities.User;

import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    public User register(User user) throws Exception {
        // transform to entity.
        // ...

        // validating...

        // saving...
        
        return user; // transform to dto.
    }

}