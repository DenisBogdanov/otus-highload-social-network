package com.bogdanium.social.network.web.model.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class ResponseProfile {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String city;
    private String interests;
    private int age;

    private List<ResponseUser> friends;

    @Setter
    private boolean isSubscribed;
}
