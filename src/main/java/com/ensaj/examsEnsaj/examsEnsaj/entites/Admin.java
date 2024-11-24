package com.ensaj.examsEnsaj.examsEnsaj.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;


}
