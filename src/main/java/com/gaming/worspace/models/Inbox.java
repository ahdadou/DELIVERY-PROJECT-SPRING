package com.gaming.worspace.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data
@Table(name = "INBOX")
public class Inbox {

    @Id
    @Column(name = "INBOX_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long  ID;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User user;

    @OneToOne
    @JoinColumn(name = "SENDER_USER_ID")
    public User user_sender;

    @OneToMany(mappedBy = "inbox")
    public List<Messages> messages = new ArrayList<>();

    private String lastMessage;



}
