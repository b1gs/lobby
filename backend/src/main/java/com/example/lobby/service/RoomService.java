package com.example.lobby.service;

import com.example.lobby.domain.Room;

import java.util.List;

public interface RoomService {

    List<Room> getAllRooms();

    Room getRoom(Long roomId);

    Room create(Room room);

    Room update(Long roomId, Room room);

    void delete(Long roomId);

}
