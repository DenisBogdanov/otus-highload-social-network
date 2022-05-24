package com.bogdanium.social.network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// TODO Bogdanov -- deploy to Heroku, https://www.youtube.com/watch?v=q7e4A9pfNrk
public class SocialNetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialNetworkApplication.class, args);
    }
}
