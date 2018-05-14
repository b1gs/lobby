package com.example.lobby.service.impl;

import com.example.lobby.domain.Room;
import com.example.lobby.repo.PlayersRepository;
import com.example.lobby.repo.RoomRepository;
import com.example.lobby.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final PlayersRepository playersRepository;

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoom(Long roomId) {
        return roomRepository.getOne(roomId);
    }

    @Override
    public Room create(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room update(Long roomId, Room room) {
        Room existingRoom = roomRepository.getOne(roomId);
        if (existingRoom == null){
            throw new IllegalArgumentException("Room(id="+roomId+")" + "  NOT EXISTS");
        }
        room.setId(roomId);
        return roomRepository.save(room);
    }

    @Override
    public void delete(Long roomId) {
        roomRepository.delete(roomRepository.getOne(roomId));
    }
}
