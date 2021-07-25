package com.gaming.worspace.models;

import com.gaming.worspace.models.audit.DateAudit;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COMMENTS")
public class Comments extends DateAudit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    @OneToMany(mappedBy = "comments")
    private List<ChildCommets> child_commets;


    public Comments() {
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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

    public List<ChildCommets> getChild_commets() {
        return child_commets;
    }

    public void setChild_commets(List<ChildCommets> child_commets) {
        this.child_commets = child_commets;
    }
}
