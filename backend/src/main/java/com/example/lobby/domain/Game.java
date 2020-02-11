package com.example.lobby.domain;


import com.example.lobby.enums.GameState;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "game")
@EqualsAndHashCode(callSuper = true, exclude = {"room"})
@ToString(callSuper = true)
public class Game extends BaseEntity {


    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "game_status")
    private GameState gameStatus;


    @Column(name = "game_type")
    private String gameType;

    @Column(name = "current_turn_number")
    private Long currentPlayerNumber;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "turn_number")
    public Long turnNumber;

    public boolean isFirstPlayerTurn() {
        return turnNumber > 0;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "game_player",
            joinColumns = {@JoinColumn(name = "player_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")})
    @ElementCollection
    @Column(name="player_turn_number")
    private Map<Long, Player> playerTurnMap;
}
