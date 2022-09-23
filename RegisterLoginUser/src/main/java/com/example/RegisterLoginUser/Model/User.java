package com.example.RegisterLoginUser.Model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table( name= "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String username;
    private String password;
}
