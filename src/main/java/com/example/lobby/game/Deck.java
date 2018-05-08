package com.example.lobby.game;

import com.example.lobby.enums.Rank;
import com.example.lobby.enums.Suit;

import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> cardDeck = new ArrayList<>(52);

    public Deck(){
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values() ) {
                Card card = new Card(suit,rank);
                cardDeck.add(card);
            }
        }

        for (int i = 0; i < 52; i++) {
            int r = i + (int) (Math.random() * (52-i));
            Card temp = cardDeck.get(r);
            cardDeck.add(r, cardDeck.get(i));
            cardDeck.add(i, temp);
        }
        for(Card card : cardDeck){
            System.out.println( card.getRank() + "    " + card.getSuit() );
        }
    }


    public ArrayList<Card> getCardDeck() {
        return cardDeck;
    }
}
