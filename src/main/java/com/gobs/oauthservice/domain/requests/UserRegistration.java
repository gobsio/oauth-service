package com.gobs.oauthservice.domain.requests;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistration implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String firstName;

    private String lastName;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String password;

}
