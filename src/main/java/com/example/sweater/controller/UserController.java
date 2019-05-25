package com.example.sweater.controller;

import com.example.sweater.domain.User;
import com.example.sweater.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
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

//    @GetMapping("/edit")
//    public String getProfile(Model model, @AuthenticationPrincipal User user) {
//        model.addAttribute("username", user.getUsername());
//
//        return "edit";
//    }
//
//    @PostMapping("/edit")
//    public String updateProfile(@AuthenticationPrincipal User user,
//                                @RequestParam String password,
//                                @RequestParam String password2,
//                                Model model
//    ) {
//        model.addAttribute("username", user.getUsername());
//
//        boolean isValidPassword = password != null && !password.isEmpty();
//        boolean isValidPassword2 = password2 != null && !password2.isEmpty();
//
//        if (!(isValidPassword && isValidPassword2)) {
//            if (!isValidPassword) {
//                model.addAttribute("passwordError", "Password cannot be empty!");
//            }
//
//            if (!isValidPassword2) {
//                model.addAttribute("password2Error", "Password confirmation cannot be empty!");
//            }
//
//            return "edit";
//        }
//
//        if (!password.equals(password2)) {
//            model.addAttribute("passwordError", "Passwords do not match!");
//            model.addAttribute("password2Error", "Passwords do not match!");
//
//            return "edit";
//        }
//
//        userSevice.updateProfile(user, password);
//
//
//        return "redirect:/user/" + user.getUsername();
//    }

}
