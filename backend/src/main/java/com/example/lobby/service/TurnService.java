package com.example.lobby.service;

import com.example.lobby.messaging.TurnMessage;

public interface TurnService {

    boolean isPlayerTurn(Long roomId, TurnMessage turnMessage);

    void makeTurn(TurnMessage turnMessage);

}
