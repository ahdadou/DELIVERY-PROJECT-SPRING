package com.gaming.worspace.models;

import javax.persistence.*;


@Entity
@Table(name = "LIKES")
public class Likes {


    @Id
    @Column(name = "LIKE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    public Likes() {
    }

    public Likes(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
