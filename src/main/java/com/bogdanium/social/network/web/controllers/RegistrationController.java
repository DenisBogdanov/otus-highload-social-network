package com.bogdanium.social.network.web.controllers;

import com.bogdanium.social.network.services.UserInfoService;
import com.bogdanium.social.network.web.model.request.RequestUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserInfoService userInfoService;

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("requestUser", new RequestUser());
        return "registrationForm";
    }

    @PostMapping("/register")
    public String processRegister(RequestUser user) {
        userInfoService.save(user);
        return "redirect:/successful-registration";
    }

    @GetMapping("/successful-registration")
    public String successfulRegistration() {
        return "successfulRegistration";
    }
}
