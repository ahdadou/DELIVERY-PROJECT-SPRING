package com.gaming.worspace.models;


import com.gaming.worspace.models.audit.DateAudit;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FOLLOWERS")
public class Follower extends DateAudit {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "FOLLOWERS_ID")
    private long id;

    @ManyToOne
    private User to;

    @ManyToOne
    private User from;

    public Follower() {
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }
}
