package com.gobs.oauthservice.controllers;

import javax.inject.Inject;

import com.gobs.oauthservice.domain.entities.oauth.OAuthClientAdditionalInformation;
import com.gobs.oauthservice.domain.entities.oauth.OAuthClientDetails;
import com.gobs.oauthservice.services.oauth.OAuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Inject
    private OAuthService oauthService;

    @PostMapping("/api/v1/oauth_clients")
    public ResponseEntity<?> registerOAuth2Client(@RequestBody OAuthClientAdditionalInformation additionalInformation) throws Exception {
        return ResponseEntity.ok(oauthService.save(additionalInformation));
    }



}