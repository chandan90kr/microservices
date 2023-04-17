package com.techotekkie.user.service.UserService.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
//TODO: This is a model class is used handle the data from the repository where we send data from or to service and impl class.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name= "micro_users")
public class User {
    @Id
    @Column(name = "ID")
    private  String userId;

    @Column(name = "NAME",length =20)
    private String name;

    @Column(name = "EMAIL")
    private  String email;

    @Column(name = "ABOUT")
    private  String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();

}
