package com.example.lobby.repo;

import com.example.lobby.domain.Room;

import java.util.LinkedList;
import java.util.List;

public class RoomRepository {

    public List<Room> getAllRooms(){
        LinkedList<Room> rooms = new LinkedList<>();
        rooms.add(new Room( "roo1" ,4  ));
        rooms.add(new Room( "roo2", 3 ));
        rooms.add(new Room( "roo3" , 2 ));
        return rooms;
    }

}
