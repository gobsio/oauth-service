package com.gobs.oauthservice.domain.entities.authorities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "authorities")
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UserAuthorityId id;

    @Column(name = "username", nullable = false, length = 256, insertable = false, updatable = false)
    private String username;

    @Column(name = "authority", nullable = false, length = 50, insertable = false, updatable = false)
    private String authority;

}
