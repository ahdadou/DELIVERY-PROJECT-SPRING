package com.gaming.worspace.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gaming.worspace.models.audit.DateAudit;
import com.gaming.worspace.models.enumerated.Gender;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
//@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
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

    @Email
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "BIRTHDAY")
    private Instant birthday;

    @Column(name = "PHONE",length = 25)
    private String phone;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;


    @ManyToOne
    @JoinColumn(name = "SERVICE_ID")
    private SERVICE_TYPE service_type;

    @OneToMany(mappedBy = "user_receiver",fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();


    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Inbox> inboxs = new ArrayList<>();


    @Column(name = "IMAGE")
    private String image;




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

    @Column(name = "CITY")
    private String city;



//    CONSTRUCTORS

    public User() {
    }


//    GETTERS  & SETTERS


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getBirthday() {
        return birthday;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }



    public SERVICE_TYPE getServiceType() {
        return service_type;
    }

    public void setServiceType(SERVICE_TYPE service) {
        this.service_type = service;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }
}
