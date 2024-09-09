package com.dailycodework.dreamshops.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;


    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;


    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;


}
