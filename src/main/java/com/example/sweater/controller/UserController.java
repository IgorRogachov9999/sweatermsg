package com.example.sweater.controller;

import com.example.sweater.domain.User;
import com.example.sweater.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class UserController {
    @Autowired
    private UserSevice userSevice;

    @GetMapping("/user/{username}")
    public String profile(@PathVariable String username, Model model) {
        User user = userSevice.findUserByUsername(username);

        if (user != null) {
            model.addAttribute("username", username);

            return "profile";
        }

        return "redirect:/";
    }

    @GetMapping("/edit")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());

        return "edit";
    }

    @PostMapping("/edit")
    public String updateProfile(Model model,
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String password2
    ) {
        if (!password.equals(password2)) {
            model.addAttribute("message", "Passwords do not match!");
        } else {
            userSevice.updateProfile(user, password);
        }

        return "redirect:/edit";
    }

}
