package com.example.sweater.controller;

import com.example.sweater.domain.User;
import com.example.sweater.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/search")
    public String search(
            @RequestParam(required = false, defaultValue = "") String username,
            Model model
    ) {
        List<User> users;

        if (username.equals("")) {
            users = userRepo.findAll();
        } else {
            users = userRepo.findUsersByUsernameLike(username);
        }

        model.addAttribute("users", users);

        return "search";
    }
}
