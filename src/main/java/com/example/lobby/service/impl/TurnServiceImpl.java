package com.example.lobby.service.impl;

import com.example.lobby.messaging.TurnMessage;
import com.example.lobby.repo.GameRepository;
import com.example.lobby.repo.RoomRepository;
import com.example.lobby.repo.TurnRepository;
import com.example.lobby.service.TurnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TurnServiceImpl implements TurnService{

    private final GameRepository gameRepository;
    private final RoomRepository roomRepository;
    private final TurnRepository turnRepository;

    @Override
    public boolean isPlayerTurn(TurnMessage turnMessage) {
        return false;
    }

    @Override
    public void makeTurn(TurnMessage turnMessage) {

    }

}
