package com.gobs.oauthservice.domain.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    static final long serialVersionUID = 1L;

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}