package com.gobs.oauthservice.repositories.oauth;

import com.gobs.oauthservice.domain.entities.oauth.OAuthClientDetails;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthClientRepository extends JpaRepository<OAuthClientDetails, String> {
}
