package com.gaming.worspace.models;


import com.gaming.worspace.models.audit.DateAudit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "POST")
public class Post extends DateAudit {

    @Id
    @Column(name = "POST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long  ID;

    @ManyToOne
    public User user;

    @Column(name = "BODY",nullable = false)
    private String body;
//
//    @OneToMany(mappedBy = "post")
//    private List<Comments> comments = new ArrayList<>();
//
//    @OneToMany(mappedBy = "post")
//    private List<Likes> likes = new ArrayList<>();

    public Post() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

//    public List<Comments> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Comments> comments) {
//        this.comments = comments;
//    }
//
//    public List<Likes> getLikes() {
//        return likes;
//    }
//
//    public void setLikes(List<Likes> likes) {
//        this.likes = likes;
//    }
}
