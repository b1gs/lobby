package com.example.lobby.service.impl;

import com.example.lobby.domain.Card;
import com.example.lobby.domain.Deck;
import com.example.lobby.domain.Player;
import com.example.lobby.repo.CardRepository;
import com.example.lobby.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
    public Set<Player> handOverCards(Set<Player> players) {

        int currentPlayer = 0;
        List<Set<Card>> playersCards = new ArrayList(players.size());

        initCardArray(playersCards , players.size());

        for ( Card card : getShuffledCards() ){
            playersCards.get(currentPlayer).add(card);
            currentPlayer++;
            if (currentPlayer > players.size() - 1){
                currentPlayer = 0;
            }
        }
        currentPlayer = 0;
        for(Player p : players){
            System.out.println("Players card before set:  " + p.getPlayerCards());
            p.setPlayerCards(playersCards.get(currentPlayer++));
        }
        for (Set<Card> cards : playersCards){
            System.out.println(cards);
        }
        return players;
    }

    private void initCardArray(List<Set<Card>> playersCards, int numberOfPlayers){
        for(int i = 0; i < numberOfPlayers; i++){
            playersCards.add(new HashSet<Card>());
        }
    }
}
