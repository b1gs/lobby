package com.example.lobby.service;

import com.example.lobby.domain.Card;
import com.example.lobby.domain.Player;

import java.util.List;
import java.util.Set;

public interface CardService {

    List<Card> getShuffled();

    Set<Player> handOverCards(Set<Player> players);

}
