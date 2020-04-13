package com.gobs.oauthservice.domain.entities.authorities;

import java.io.Serializable;
import javax.persistence.Column;

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

    @Column(name = "username", nullable = false, length = 256)
    private String username;

    @Column(name = "authority", nullable = false, length = 50)
    private String authority;

}
