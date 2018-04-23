package com.example.lobby.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username" , nullable = false )
    private String username;

    @Column(name = "email" , nullable = false , unique = true)
    private String email;

}
