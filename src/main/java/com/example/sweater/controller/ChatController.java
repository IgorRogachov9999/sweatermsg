package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.model.Chat;
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
        List<Chat> chats = messageService.getChatsMessages(user.getId());

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

            List<Message> chat = messageService.getChat(user1.getId(), user2.getId());

            if (chat.size() > 0) {
                Message lastMessage = chat.get(0);

                if (lastMessage.getReceiverId() == user.getId() &&
                        !lastMessage.isRead()) {
                    for (int i = 0; i < chat.size(); i++) {
                        Message msg = chat.get(i);
                        if (!msg.isRead()) {
                            msg.setRead(true);
                            messageRepo.save(msg);
                        } else break;
                    }
                }
            }

            List<User> users = new LinkedList<>();

            users.add(user1);
            users.add(user2);

            model.addAttribute("chat", chat);
            model.addAttribute("users", users);

            return "chat";
        }

        return "redirect:/";
    }

    @PostMapping("/chat/{username1}/{username2}")
    public String takeMessage(@AuthenticationPrincipal User user,
                              Message message,
                       @PathVariable String username1,
                       @PathVariable String username2
    ) {
        if ((user.getUsername().equals(username1) ||
                user.getUsername().equals(username2)) &&
                !username1.equals(username2)) {

                User receiver = userSevice.findUserByUsername(
                        user.getUsername().equals(username1) ? username2 : username1);

                message.setSenderId(user.getId());
                message.setReceiverId(receiver.getId());
                message.setRead(false);
                message.setTimestamp(new Date().getTime());

                messageRepo.save(message);

            return "redirect:/chat/" + username1 + "/" + username2;
        }

        return "redirect:/";
    }
}
