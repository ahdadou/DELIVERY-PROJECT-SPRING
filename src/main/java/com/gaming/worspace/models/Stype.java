package com.gaming.worspace.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gaming.worspace.models.enumerated.Type;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Stype")
public class Stype{ // User Service Type


    @Id
    @Column(name = "SERVICE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long  id;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    @NaturalId
    private Type type;


    @OneToMany(mappedBy = "stype")
    @JsonIgnore
    private List<User> users = new ArrayList<>();


    public long getId() {
        return id;
    }

    public void setId(long ID) {
        this.id = ID;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
