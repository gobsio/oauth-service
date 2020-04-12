package com.gobs.oauthservice.domain.dtos;

import java.io.Serializable;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor // @formatter:off
@EqualsAndHashCode
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigInteger id;

    private String username;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    private String email;

    private String phone;

    private String firstName;

    private String lastName;

    private Boolean active;

    private Boolean activated;

    private String picture; 

}