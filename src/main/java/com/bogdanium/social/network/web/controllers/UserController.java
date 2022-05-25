package com.bogdanium.social.network.web.controllers;

import com.bogdanium.social.network.services.UserInfoService;
import com.bogdanium.social.network.web.model.response.ResponseProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserInfoService userInfoService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/personal-page")
    public String personalPage(Model model) {
        model.addAttribute("userProfile", userInfoService.currentProfile());
        return "personalPage";
    }

    @GetMapping("/users")
    public String users(Model model) {
        ResponseProfile currentUser = userInfoService.currentProfile();
        model.addAttribute("users", userInfoService.findAllUsers().stream()
                .filter(user -> !user.getId().equals(currentUser.getId()))
                .collect(Collectors.toList()));

        return "users";
    }

    @GetMapping("/users/{userId}")
    public String findUser(@PathVariable int userId, Model model) {
        ResponseProfile currentUser = userInfoService.currentProfile();
        if (currentUser.getId().equals(userId)) {
            return "redirect:/personal-page";
        }

        ResponseProfile userProfile = userInfoService.userProfile(userId);

        boolean isFriend = currentUser.getFriends().stream()
                .anyMatch(friend -> friend.getId().equals(userProfile.getId()));
        userProfile.setSubscribed(isFriend);

        model.addAttribute("userProfile", userProfile);
        return "user";
    }

    @PostMapping("/subscribe")
    public String subscribe(@RequestParam int id) {
        userInfoService.subscribe(id);
        return "redirect:/users/" + id;
    }
}
