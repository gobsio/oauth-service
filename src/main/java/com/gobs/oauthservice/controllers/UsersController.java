package com.gobs.oauthservice.controllers;

import javax.inject.Inject;

import com.gobs.oauthservice.services.user.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    @Inject
    UserService userService;;

    @GetMapping
    public ResponseEntity<?> fetch() throws Exception {
        return ResponseEntity.ok(userService.fetch());
    }

}