package com.example.lobby.repo;

import com.example.lobby.domain.Room;

import java.util.LinkedList;
import java.util.List;

public class RoomRepository {

    public List<Room> getAllRooms(){
        LinkedList<Room> rooms = new LinkedList<>();
        rooms.add(new Room( "roo1" ));
        rooms.add(new Room( "roo2" ));
        rooms.add(new Room( "roo3" ));
        return rooms;
    }

}
