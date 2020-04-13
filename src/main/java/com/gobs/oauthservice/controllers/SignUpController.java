package com.gobs.oauthservice.controllers;

import javax.inject.Inject;
import javax.validation.Valid;

import com.gobs.oauthservice.domain.requests.UserRegistration;
import com.gobs.oauthservice.services.signup.SignUpService;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    @Inject
    SignUpService service;

    @PostMapping("/register")
    public ResponseEntity<?> signup(@Valid @RequestBody UserRegistration resgistration, Model model) throws Exception {
        return ResponseEntity.ok(service.register(resgistration));
    }

}
