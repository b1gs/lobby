package com.example.lobby.service;

import com.example.lobby.domain.Game;
import com.example.lobby.domain.Player;
import com.example.lobby.domain.Room;

import java.util.List;
import java.util.Set;

public interface GameService {

    Game create(Set<Player> firstPlayer, Room Room);

}
