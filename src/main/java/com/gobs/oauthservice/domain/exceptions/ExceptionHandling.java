package com.gobs.oauthservice.domain.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

import java.util.Locale;

import com.gobs.oauthservice.domain.exceptions.user.UserAlreadyRegisteredException;
import com.gobs.oauthservice.domain.exceptions.user.UserNotFoundException;
import com.gobs.oauthservice.domain.responses.ErrorResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler({ RuntimeException.class })
    public HttpEntity<?> handleRunTimeException(RuntimeException e, Locale locale) {
        return error(INTERNAL_SERVER_ERROR, "internal_server_error", e.getMessage(), e);
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public HttpEntity<?> handleAccessDeniedException(AccessDeniedException e, Locale locale) {
        return error(BAD_REQUEST, "access_denied", e.getMessage(), e);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public HttpEntity<?> handleIllegalArgumentException(RuntimeException e, Locale locale) {
        return error(BAD_REQUEST, "illegal_arguments", e.getMessage(), e);
    }

    /*
    @ExceptionHandler(FeignException.class)
    public HttpEntity<?> handleFeignException(FeignException e) {
        return error(HttpStatus.valueOf(e.status()), "internal_server_error", e.contentUTF8(), e);
    }

    @ExceptionHandler(FeignException.GatewayTimeout.class)
    public HttpEntity<?> handleFeignGatewayTimeoutException(FeignException e) {
        return error(HttpStatus.valueOf(e.status()), "gateway_timeout", e.contentUTF8(), e);
    }

    @ExceptionHandler(FeignException.BadRequest.class)
    public HttpEntity<?> handleFeignBadRequestException(FeignException e) {
        return error(HttpStatus.valueOf(e.status()), "bad_request", e.contentUTF8(), e);
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public HttpEntity<?> handleFeignNotFoundException(FeignException e) {
        return error(HttpStatus.valueOf(e.status()), "not_found", e.contentUTF8(), e);
    }

    @ExceptionHandler(FeignException.TooManyRequests.class)
    public HttpEntity<?> handleFeignTooManyRequestsException(FeignException e) {
        return error(HttpStatus.valueOf(e.status()), "too_many_requests", e.contentUTF8(), e);
    }
    */

    @ExceptionHandler({ UserNotFoundException.class })
    public HttpEntity<?> handleUserNotFoundException(UserNotFoundException e, Locale locale) {
        return error(NOT_FOUND, "user_not_found", e.getMessage(), e);
    }

    @ExceptionHandler({ UserAlreadyRegisteredException.class })
    public HttpEntity<?> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException e, Locale locale) {
        return error(CONFLICT, "user_already_exists", e.getMessage(), e);
    }

    private HttpEntity<?> error(HttpStatus status, String error, String errorDescription, Exception e) {
        // e.printStackTrace();
        ErrorResponse response = new ErrorResponse(error, errorDescription);
        return ResponseEntity.status(status).body(response);
    }

}