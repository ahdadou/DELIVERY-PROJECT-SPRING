package com.gaming.worspace.models;


import com.gaming.worspace.models.audit.DateAudit;

import javax.persistence.*;

@Entity
@Table(name = "CHILD_COMMENTS")
public class ChildCommets  extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @ManyToOne
    private Comments comments;

    @ManyToOne
    @JoinColumn(name = "USER_ID")

    private User user;


    public ChildCommets(Comments comments, User user) {
        this.comments = comments;
        this.user = user;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
