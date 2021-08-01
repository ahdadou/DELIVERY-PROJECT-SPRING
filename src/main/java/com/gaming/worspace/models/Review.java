package com.gaming.worspace.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gaming.worspace.models.audit.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "REVIEW")
@NoArgsConstructor
@AllArgsConstructor
public class Review extends DateAudit {

    @Id
    @Column(name = "REVIEW_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long  ID;

    @ManyToOne
    @JoinColumn(name = "RECEIVER_USER_ID")
    @JsonIgnore
    public User user_receiver;

    @OneToOne
    @JoinColumn(name = "SENDER_USER_ID")
    public User user_sender;

    @Column(name = "RATING",nullable = false)
    private int rating;


    @Column(name = "BODY")
    private String body;


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public User getUser_receiver() {
        return user_receiver;
    }

    public void setUser_receiver(User user_receiver) {
        this.user_receiver = user_receiver;
    }

    public User getUser_sender() {
        return user_sender;
    }

    public void setUser_sender(User user_sender) {
        this.user_sender = user_sender;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
