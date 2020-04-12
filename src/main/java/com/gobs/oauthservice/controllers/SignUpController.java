package com.gobs.oauthservice.controllers;

import com.gobs.oauthservice.domain.entities.User;
import com.gobs.oauthservice.domain.exceptions.user.UserAlreadyRegisteredException;
import com.gobs.oauthservice.services.signup.SignUpService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {

    // Logger logger = LoggerFactory.getLogger(SignUpController.class);

    SignUpService service;

    @PostMapping("/register")
    public String signup(@RequestBody User user, Model model) throws Exception {

        try {
            System.out.println("Registering new user...");
            user = service.register(user);
            System.out.println("User is registered...");

            // if no exceptions was thrown adds a success attribute.
            model.addAttribute("success", "true");

        } catch (UserAlreadyRegisteredException ex) {
            model.addAttribute("error_message", "Um usuário com este endereço de email já está cadastrado!");
        } catch (Exception ex) {
            model.addAttribute("error_message", "Oops, algo deu errado!");
        }

        return "signup.html";
    }

}