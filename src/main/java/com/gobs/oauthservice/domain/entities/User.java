package com.gobs.oauthservice.domain.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "users")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor // @formatter:off
@EqualsAndHashCode
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter @Setter
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "users_sequence", sequenceName = "users_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    private BigInteger id;

    @Getter @Setter
    @Column(name = "username", unique = true, updatable = false, nullable = false)
    private String username;

    @Getter @Setter
    @Column(name = "password")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @Getter @Setter
    @Column(name = "email")
    private String email;

    @Getter @Setter
    @Column(name = "phone")
    private String phone;

    @Getter @Setter
    @Column(name = "first_name")
    private String firstName;

    @Getter @Setter
    @Column(name = "last_name")
    private String lastName;

    @Getter @Setter
    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean active;

    @Getter @Setter
    @Column(name = "activated")
    private Boolean activated;

    @Getter @Setter
    @Column(name = "picture")
    private String picture; 

    @PrePersist
    public void prePersist() {
        if (this.active == null) {
            this.active = true;
        }
    }

    @PreUpdate
    public void preUpdate() {
        if (this.active == null) {
            this.active = true;
        }
    }

}