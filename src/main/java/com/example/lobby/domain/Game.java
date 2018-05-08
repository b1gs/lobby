package com.example.lobby.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "game")
@EqualsAndHashCode(callSuper = true, exclude = {"room"})
@ToString(callSuper = true)
public class Game extends BaseEntity{


    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "game_status")
    private String gameStatus;


    @Column(name = "game_type")
    private String gameType;

    @Column(name = "first_turn_player_id")
    private Long firstTurnPlayerId;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

}
