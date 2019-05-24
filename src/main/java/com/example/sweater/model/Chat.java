package com.example.sweater.model;

import com.example.sweater.domain.Message;

public class Chat {
    private String username;
    private Message message;
    private String receiver;

    public Chat() {
    }

    public Chat(String username, Message message, String receiver) {
        this.username = username;
        this.message = message;
        this.receiver = receiver;
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
