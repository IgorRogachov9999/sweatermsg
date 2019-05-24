package com.example.sweater.service;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.repos.MessageRepo;
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

    public List<Message> getChatsMessages(User user) {
        List<Message> allMessages = (List<Message>) messageRepo.findAll();
        List<Message> lastMessages = new LinkedList<>();
        Set<User> users = new HashSet<>();

//        for (int i = allMessages.size() - 1; i >= 0; i--) {
//            List<User> msgUsers = allMessages.get(i).getUsers();
//
//            if (msgUsers.contains(user)) {
//                // if 0 -> 1 else 1 -> 0
//                User newUser = msgUsers.get(msgUsers.indexOf(user) ^ 1);
//                if (!users.contains(newUser)) {
//                    users.add(newUser);
//
//                    List<User> newUserList = new LinkedList<>();
//                    newUserList.add(newUser);
//
//                    Message msg = allMessages.get(i);
//
//                    msg.setUsers(newUserList);
//
//                    lastMessages.add(msg);
//                }
//            }
//        }

        return lastMessages;
    }

    public List<Message> getChat(User user1, User user2) {
        List<Message> allMessages = (List<Message>) messageRepo.findAll();
        List<Message> chat = new LinkedList<>();

        for (int i = allMessages.size() - 1; i >= 0; i--) {
            List<User> users = allMessages.get(i).getUsers();

            if (users.contains(user1) && users.contains(user2)) {
                chat.add(allMessages.get(i));
            }
        }

        return chat;
    }
}