package com.bogdanium.social.network.web.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseUser {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String city;
    private String interests;
    private int age;

    private boolean isFriend;
}
