package com.example.lobby.repo;

import com.example.lobby.domain.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RoomRepository {

    @Query("SELECT a from Room r WHERE active = true")
    List<Room> findAllActive();

}
