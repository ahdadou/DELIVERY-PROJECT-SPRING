package com.gaming.worspace.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gaming.worspace.models.audit.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "MESSAGES")
public class Messages extends DateAudit {

    @Id
    @Column(name = "MESSAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long  Id;

    @OneToOne
    @JoinColumn(name = "SENDER_USER_ID")
    public User user_sender;

    private String msg;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "INBOX_ID")
    private Inbox inbox;

    public long getId() {
        return Id;
    }

    public void setId(long ID) {
        this.Id = ID;
    }

    public User getUser_sender() {
        return user_sender;
    }

    public void setUser_sender(User user_sender) {
        this.user_sender = user_sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Inbox getInbox() {
        return inbox;
    }

    public void setInbox(Inbox inbox) {
        this.inbox = inbox;
    }
}
