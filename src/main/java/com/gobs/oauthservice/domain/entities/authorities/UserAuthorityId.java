package com.gobs.oauthservice.domain.entities.authorities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthorityId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "username")
    private String username;

    @Column(name = "authority")
    private String authority;

}