package com.example.sweater.model;

import com.example.sweater.domain.Message;

public class Chat {
    private String username;
    private Message message;

    public Chat() {
    }

    public Chat(String username, Message message) {
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
