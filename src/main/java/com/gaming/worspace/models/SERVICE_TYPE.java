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
@Table(name = "SERVICE_TYPE")
public class SERVICE_TYPE {


    @Id
    @Column(name = "SERVICE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long  ID;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    @NaturalId
    private Type type;


    @OneToMany(mappedBy = "service_type")
    @JsonIgnore
    private List<User> users = new ArrayList<>();


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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
