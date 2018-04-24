package com.example.lobby.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Set;

@Entity
@Table(name="room")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Room extends BaseEntity {

    private static Logger LOGGER = LoggerFactory.getLogger(Room.class);

    @Column(name = "room_name")
    private String roomName;

    @Transient
    private int capacity;

    @Transient
    private Set<Player> participants;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
