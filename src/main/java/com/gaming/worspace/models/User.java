package com.gaming.worspace.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gaming.worspace.models.audit.DateAudit;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@Table(name = "USERS")
public class User extends DateAudit {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "USERNAME",length = 25)
    private String username;

    @Column(name = "FIRSTNAME",length = 25)
    private String firstname;

    @Column(name = "LASTNAME",length = 25)
    private String lastname;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "BIRTHDAY")
    private Instant birthday;

    @Column(name = "PHONE",length = 25)
    private String phone;

    @OneToMany(mappedBy = "to")
    private List<Follower> followers;

    @OneToMany(mappedBy = "from")
    private Set<Follower> following;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Comments> comments;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @Column(name = "IAMGE")
    private String image;

//    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
//    private List<Likes> likes = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<ChildCommets> childComments;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE} )
    @JoinTable(name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID",referencedColumnName = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID",referencedColumnName = "ROLE_ID")}
    )
    private Set<Role> roles = new HashSet<>();


    @Column(name = "IS_ACTIVE",nullable = false)
    private Boolean active;

    @Column(name = "IS_EMAIL_VERIFIED", nullable = false)
    private Boolean isEmailVerified;






//    CONSTRUCTORS




//    public User(User user) {
//        id = user.getId();
//        username = user.getUsername();
//        password = user.getPassword();
//        firstname = user.getFirstname();
//        lastname = user.getLastname();
//        email = user.getEmail();
//        active = user.getActive();
//        roles = user.getRoles();
//        phone = user.getPhone();
//        birthday = user.getBirthday();
//        isEmailVerified = user.getIsEmailVerified();
//    }


//    GETTERS  & SETTERS




}
