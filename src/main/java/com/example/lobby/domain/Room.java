package com.example.lobby.domain;

import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="room")
public class Room {

    private static Logger LOGGER = Logger.getLogger(Room.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "room_name")
    private String roomName;

    @Transient
    private int capacity;

    @Transient
    private Set<Player> participants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
