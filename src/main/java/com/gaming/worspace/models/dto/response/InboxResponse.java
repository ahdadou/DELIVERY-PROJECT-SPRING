package com.gaming.worspace.models.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gaming.worspace.models.Messages;
import com.gaming.worspace.models.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

public class InboxResponse {

    public long  id;

    public UserResponse user;

    public UserResponse connectedUser;

    @JsonIgnore
    public List<Messages> messages ;

    private String lastMessage;

    public InboxResponse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public UserResponse getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(UserResponse connectedUser) {
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
