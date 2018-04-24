package com.example.lobby.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "player")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Player extends BaseEntity {


    @Column(name = "username" , nullable = false )
    private String username;

    @Column(name = "email" , nullable = false , unique = true)
    private String email;

}
