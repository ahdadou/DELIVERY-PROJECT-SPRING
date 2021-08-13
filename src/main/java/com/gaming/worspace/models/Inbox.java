package com.gaming.worspace.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "INBOX")
public class Inbox {

    @Id
    @Column(name = "INBOX_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long  Id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "USER_ID")
    public User user;

    @OneToOne
    @JoinColumn(name = "CONNECTED_USER_ID")
    public User connectedUser;

    @OneToMany(mappedBy = "inbox")
    @JsonIgnore
    public List<Messages> messages = new ArrayList<>();

    private String lastMessage;

    public long getId() {
        return Id;
    }

    public void setId(long ID) {
        this.Id = ID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(User connectedUser) {
        this.connectedUser = connectedUser;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
