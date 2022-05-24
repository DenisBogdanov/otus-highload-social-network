package com.bogdanium.social.network.db.entities;

import com.bogdanium.social.network.db.entities.enums.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class UserEntity {
    private final Integer id;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final Integer age;
    private final Gender gender;
    private final String city;
    private final String interests;

    @Setter
    private String password;
}
