package com.example.lobby.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "player")
@EqualsAndHashCode(callSuper = true, exclude = {"room"})
@ToString(callSuper = true)
public class Player extends BaseEntity {


    @Column(name = "username" , nullable = false )
    private String username;

    @Column(name = "email" , nullable = false , unique = true)
    private String email;

    @Column(name = "password" , nullable = false )
    private String password;

    @Column(name = "is_ready" , nullable = true )
    private boolean isReady;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", columnDefinition = "int8", nullable = true)
    private Room room;

}
