package com.bogdanium.social.network;

import com.bogdanium.social.network.services.UserInfoService;
import com.bogdanium.social.network.web.model.request.RequestUser;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConditionalOnProperty(value = "generate-users", havingValue = "true")
@RequiredArgsConstructor
public class UsersGenerator implements CommandLineRunner {
    private final UserInfoService userInfoService;
    private final Faker faker = Faker.instance();

    @Override
    public void run(String... args) throws Exception {
        List<RequestUser> users = new ArrayList<>(100);
        for (int i = 1; i <= 1_000_000; i++) {
            users.add(createUser(i));
            if (users.size() % 1_000 == 0) {
                userInfoService.saveAll(users);
                users.clear();
            }
        }
    }

    private RequestUser createUser(int index) {
        RequestUser user = new RequestUser();
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(user.getFirstName() + user.getLastName() + index + "@gmail.com");
        return user;
    }
}
