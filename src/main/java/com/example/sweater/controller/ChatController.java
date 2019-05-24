package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.repos.MessageRepo;
import com.example.sweater.service.MessageService;
import com.example.sweater.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class ChatController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserSevice userSevice;

    @Autowired
    private MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String main(Model model, @AuthenticationPrincipal User user) {
        List<Message> chats = messageService.getChatsMessages(user);

        model.addAttribute("chats", chats);

        return "chats";
    }

    @GetMapping("/chat/{username1}/{username2}")
    public String chat(Model model,
                       @AuthenticationPrincipal User user,
                       @PathVariable String username1,
                       @PathVariable String username2
    ) {
        if ((user.getUsername().equals(username1) ||
                user.getUsername().equals(username2)) &&
                !username1.equals(username2)) {

            User user1 = userSevice.findUserByUsername(username1);
            User user2 = userSevice.findUserByUsername(username2);

            List<Message> chat = messageService.getChat(user1, user2);

            model.addAttribute("chat", chat);

            return "chat";
        }

        return "redirect:/";
    }

    @PostMapping("/chat/{username1}/{username2}")
    public String takeMessage(@AuthenticationPrincipal User user,
                              Message message,
                       @PathVariable String username1,
                       @PathVariable String username2,
                       Model model
    ) {
        if ((user.getUsername().equals(username1) ||
                user.getUsername().equals(username2)) &&
                !username1.equals(username2)) {

                User receiver = userSevice.findUserByUsername(
                        user.getUsername().equals(username1) ? username2 : username1);

                List<User> users = new LinkedList<>();
                users.add(user);
                users.add(receiver);

                message.setUsers(users);
                message.setRead(false);
                message.setTimestamp(new Date().getTime());

                messageRepo.save(message);

            return "redirect:/chat/" + username1 + "/" + username2;
        }

        return "redirect:/";
    }
}
