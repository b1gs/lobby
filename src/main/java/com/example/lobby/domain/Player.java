package com.example.lobby.domain;

import com.example.lobby.game.Card;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "player")
@EqualsAndHashCode(callSuper = true, exclude = {"room", "playerCards"})
@ToString(callSuper = true)
public class Player extends BaseEntity {


    @Column(name = "username" , nullable = false )
    private String username;

    @Column(name = "email" , nullable = false , unique = true)
    private String email;

    @Column(name = "password" , nullable = false )
    private String password;

    @Column(name = "is_ready" , nullable = true )
    private boolean isReady = true;

    @ManyToOne
    @JoinColumn(name = "room_id", columnDefinition = "int8", nullable = true)
    private Room room;

    @ManyToMany
    @JoinTable( name = "player_card" ,
                joinColumns = { @JoinColumn(name = "player_id")},
                inverseJoinColumns = {@JoinColumn(name = "card_id")})
    private Set<Card> playerCards;

}
