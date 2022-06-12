package com.bogdanium.social.network.web.controllers;

import com.bogdanium.social.network.services.UserInfoService;
import com.bogdanium.social.network.web.model.response.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {
    private final UserInfoService userInfoService;

    // http://localhost:8081/api/users/byPrefix?prefix=ab
    @GetMapping("/users/byPrefix")
    public List<ResponseUser> usersByPrefix(@RequestParam String prefix) {
        return userInfoService.findByPrefix(prefix);
    }
}
