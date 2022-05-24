package com.bogdanium.social.network.components;


import com.bogdanium.social.network.db.entities.UserEntity;
import com.bogdanium.social.network.db.entities.enums.Gender;
import com.bogdanium.social.network.web.model.request.RequestUser;
import com.bogdanium.social.network.web.model.response.ResponseProfile;
import com.bogdanium.social.network.web.model.response.ResponseUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public UserEntity toUserEntity(RequestUser user) {
        return UserEntity.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .gender(Gender.get(user.getGender()))
                .city(user.getCity())
                .interests(user.getInterests())
                .age(user.getAge())
                .password(user.getPassword())
                .build();
    }

    public ResponseUser toResponseUser(UserEntity user) {
        return ResponseUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .gender(user.getGender().name())
                .city(user.getCity())
                .interests(user.getInterests())
                .age(user.getAge())
                .build();
    }

    public ResponseProfile toResponseProfile(UserEntity user, List<UserEntity> friends) {
        return ResponseProfile.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .gender(user.getGender().name())
                .city(user.getCity())
                .interests(user.getInterests())
                .age(user.getAge())
                .friends(friends.stream().map(this::toResponseUser).toList())
                .build();
    }
}
