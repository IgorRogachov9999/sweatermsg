package com.example.sweater.domain;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String text;
    private Long timestamp;
    private boolean read;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            })
    @JoinTable(
            name = "message_user",
            joinColumns = { @JoinColumn(name = "message_id", referencedColumnName="id")},
            inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName="id") }
    )
    List<User> users = new LinkedList<>();

    public Message() {

    }

    public Message(String text) {
        this.text = text;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
