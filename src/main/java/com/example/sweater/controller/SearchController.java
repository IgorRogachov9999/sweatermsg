package com.example.sweater.controller;

import com.example.sweater.domain.User;
import com.example.sweater.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class SearchController {
    @Autowired
    private UserSevice userSevice;

    @GetMapping("/search")
    public String search(
            @RequestParam(required = false, defaultValue = "") String username,
            Model model
    ) {
        List<User> users;

        if (username.equals("")) {
            users = userSevice.findAll();
        } else {
            users = userSevice.loadUsersByUsernameLike(username);
        }

        model.addAttribute("users", users);

        return "search";
    }
}
