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
    public Map<Integer,Player> handOverCards(Map<Integer,Player> playersTurnMap) {

        int currentPlayer = 1;
        int prikupCardsCounter = 0;
        int playersInGame = playersTurnMap.size();

        for ( Card card : getShuffledCards() ){
            if (prikupCardsCounter < playersInGame * 2 ) {
                card.setPrikup(true);
                prikupCardsCounter++;
                playersTurnMap.get(currentPlayer).getPlayerPrikup().add(card);
            }else {
                playersTurnMap.get(currentPlayer).getPlayerCards().add(card);
            }
            currentPlayer++;
            if (currentPlayer > playersInGame){
                currentPlayer = 0;
            }
        }

        return playersTurnMap;
    }
}
