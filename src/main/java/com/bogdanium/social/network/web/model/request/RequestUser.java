package com.bogdanium.social.network.web.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RequestUser {
    private Integer id;

    @NotBlank
    @Email
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String city;
    private String interests;
    private int age;

    private String password;
}
