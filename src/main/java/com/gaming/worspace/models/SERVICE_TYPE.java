package com.gaming.worspace.models;

import com.gaming.worspace.models.enumerated.Type;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
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
    private List<User> users = new ArrayList<>();



}
