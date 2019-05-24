package com.example.sweater.service;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.model.Chat;
import com.example.sweater.repos.MessageRepo;
import com.example.sweater.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserRepo userRepo;

    public List<Chat> getChatsMessages(Long id) {
        List<Message> allMessages = (List<Message>) messageRepo.findAll();
        List<Chat> chats = new LinkedList<>();
        Set<Long> ids = new HashSet<>();

        for (int i = allMessages.size() - 1; i >= 0; i--) {
            Message message = allMessages.get(i);

            if (message.getReceiverId().equals(id) || message.getSenderId().equals(id)) {
                Long uid =
                        message.getReceiverId().equals(id) ? message.getSenderId() : message.getReceiverId();

                if (!ids.contains(uid)) {
                    ids.add(uid);
                    User user = userRepo.findUsersById(uid);
                    Chat chat = new Chat(user.getUsername(), message);
                    chats.add(chat);
                }
            }
        }

        return chats;
    }

    public List<Message> getChat(Long id1, Long id2) {
        List<Message> allMessages = (List<Message>) messageRepo.findAll();
        List<Message> chat = new LinkedList<>();
        Set<Long> users = new HashSet<>();

        users.add(id1);
        users.add(id2);

        for (int i = allMessages.size() - 1; i >= 0; i--) {
            Message message = allMessages.get(i);

            if (users.contains(message.getReceiverId()) && users.contains(message.getSenderId())) {
                chat.add(message);
            }
        }

        return chat;


    }
}
