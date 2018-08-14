package com.example.lobby.service;

import com.example.lobby.messaging.TurnMessage;

public interface TurnService {

    boolean isPlayerTurn(TurnMessage turnMessage);
    void makeTurn(TurnMessage turnMessage);

}
