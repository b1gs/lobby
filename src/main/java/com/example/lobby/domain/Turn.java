package com.example.lobby.domain;


import com.example.lobby.enums.Rank;
import com.example.lobby.enums.Suit;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "turn")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Turn extends BaseEntity{


    @Column(name = "rank", nullable = false)
    private Rank rank;

    @Column(name = "suit", nullable = false)
    private Suit suit;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

}
