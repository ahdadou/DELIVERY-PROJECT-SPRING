package com.gaming.worspace.models;

import com.gaming.worspace.models.audit.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "MESSAGES")
public class Messages extends DateAudit {

    @Id
    @Column(name = "MESSAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long  ID;

    @OneToOne
    @JoinColumn(name = "SENDER_USER_ID")
    public User user_sender;

    private String msg;

    @ManyToOne
    @JoinColumn(name = "INBOX_ID")
    private Inbox inbox;






}
