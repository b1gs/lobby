package com.example.lobby.service;

import com.example.lobby.domain.Game;
import com.example.lobby.domain.Player;
import com.example.lobby.domain.Room;

public interface GameService {

    Game create(Player firstPlayer, Room Room);

}
