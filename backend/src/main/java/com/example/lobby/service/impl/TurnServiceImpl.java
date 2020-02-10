package com.example.lobby.service.impl;

import com.example.lobby.domain.Game;
import com.example.lobby.messaging.TurnMessage;
import com.example.lobby.repo.GameRepository;
import com.example.lobby.repo.PlayerRepository;
import com.example.lobby.repo.RoomRepository;
import com.example.lobby.repo.TurnRepository;
import com.example.lobby.service.TurnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TurnServiceImpl implements TurnService {

    private final GameRepository gameRepository;
    private final RoomRepository roomRepository;
    private final TurnRepository turnRepository;
    private final PlayerRepository playerRepository;

    @Override
    public boolean isPlayerTurn(Long roomId, TurnMessage turnMessage) {
        Game game = gameRepository.findFirstByRoomId(roomId);
        return false;
    }

    @Override
    public void makeTurn(TurnMessage turnMessage) {

    }

}
