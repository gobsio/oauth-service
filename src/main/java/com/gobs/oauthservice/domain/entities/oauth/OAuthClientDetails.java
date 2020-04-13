package com.gobs.oauthservice.domain.entities.oauth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "oauth_client_details")
public class OAuthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    @Column(name = "client_id", length = 256)
    private String clientId;

    @Getter
    @Setter
    @Column(name = "resource_ids", length = 256)
    private String resourceIds;

    @Getter
    @Setter
    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "client_secret", length = 256)
    private String clientSecret;

    @Getter
    @Setter
    @Column(name = "scope", length = 256)
    private String scope = "read,write,trust";

    @Getter
    @Setter
    @Column(name = "authorized_grant_types", length = 256)
    private String authorizedGrantTypes = "password,authorization_code,refresh_token,implicit";

    @Getter
    @Setter
    @Column(name = "web_server_redirect_uri", length = 256)
    private String webServerRedirectUri;

    @Getter
    @Setter
    @Column(name = "authorities", length = 256)
    private String authorities;

    @Getter
    @Setter
    @Column(name = "access_token_validity")
    private Long accessTokenValidity = 3600L; // seconds

    @Getter
    @Setter
    @Column(name = "refresh_token_validity")
    private Long refreshTokenValidity = 21600L; // seconds

    @Getter
    @Setter
    @Convert(converter = OAuthClientAdditionalInformationConverter.class)
    @Column(name = "additional_information", length = 4096)
    private OAuthClientAdditionalInformation additionalInformation;

    @Getter
    @Setter
    @Column(name = "autoapprove", length = 4096)
    private String autoapprove = "trust,read,write";

}
