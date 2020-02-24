package com.example.lobby.service.impl;

import com.example.lobby.domain.Card;
import com.example.lobby.domain.Deck;
import com.example.lobby.domain.Player;
import com.example.lobby.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private List<Card> cardDeck = Deck.getDeck();

    @Override
    public List<Card> getShuffledCards() {
        List<Card> copy = new ArrayList<>(cardDeck);
        Collections.shuffle(copy);
        return copy;
    }

    @Override
    public Map<Integer, Player> handOverCards(Map<Integer, Player> playersTurnMap) {

        int currentPlayer = 1;
        int playersInGame = playersTurnMap.size();

        for (Card card : getShuffledCards()) {
            Player player = playersTurnMap.get(currentPlayer);
            if (player.getPlayerPrikup().size() < 2) {
                card.setPrikup(true);
                player.getPlayerPrikup().add(card);
            } else {
                player.getPlayerCards().add(card);
            }
            currentPlayer = getNextTurnPlayerNumber(++currentPlayer, playersInGame);
        }

        return playersTurnMap;
    }

    private int getNextTurnPlayerNumber(int currentPlayerNumber, int playersInGame) {
        if (currentPlayerNumber > playersInGame) {
            currentPlayerNumber = 1;
        }
        return currentPlayerNumber;
    }
}
