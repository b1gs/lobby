package com.example.lobby.service.impl;

import com.example.lobby.domain.Game;
import com.example.lobby.domain.Player;
import com.example.lobby.domain.Room;
import com.example.lobby.enums.GameState;
import com.example.lobby.repo.GameRepository;
import com.example.lobby.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Override
    public Game create(Player firstPlayer, Room room) {
        Game game = new Game();
        game.setFirstTurnPlayerId(firstPlayer.getId());
        game.setRoom(room);
        game.setGameStatus(GameState.IN_PROGRESS);
        return gameRepository.save(game);
    }
}
