package com.example.lobby.domain;

import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class Room {

    private static Logger LOGGER = Logger.getLogger(Room.class);

    private final String roomName;
    private final int capacity;
    private Set<Player> participants;

    public Room(String roomName, int capacity  ){
        this.roomName = roomName;
        this.capacity = capacity;
        this.participants =  new HashSet<Player>(capacity);
    }

    public String getRoomName(){
        return this.roomName;
    }


    public void addPlayer(Player player ){
        if (participants.size() < capacity ){
            participants.add(player);
        }else {
            LOGGER.info("Room is Full : " + roomName);
        }
    }

    public void removePlayer(Player player ){
        participants.remove(player);
    }

}
