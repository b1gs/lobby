package com.example.lobby.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "room")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"playerTurnMap"})
@ToString(callSuper = true)
public class Room extends BaseEntity {

    private static Logger LOGGER = LoggerFactory.getLogger(Room.class);

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @OneToOne
    @JoinColumn(name = "owner")
    private Player owner;

    @OneToMany(targetEntity = Player.class, mappedBy = "room")
    private Set<Player> players;

}
