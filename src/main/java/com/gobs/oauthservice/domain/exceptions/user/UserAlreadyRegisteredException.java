package com.gobs.oauthservice.domain.exceptions.user;

public class UserAlreadyRegisteredException extends RuntimeException {

    static final long serialVersionUID = 1L;

    public UserAlreadyRegisteredException(String errorMessage) {
        super(errorMessage);
    }

}
