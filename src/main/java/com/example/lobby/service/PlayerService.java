package com.example.lobby.service;

import com.example.lobby.domain.Player;
import com.example.lobby.domain.Room;

import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayers();

    Player getPlayer(Long id);

    Player create(Player player);

    Player update(Long id , Player player);

    void delete(Long id);

    void addPlayerToRoom(Player player , Room room);

    void removePlayerFromRoom(Player player , Room room);

}
