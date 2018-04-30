package com.example.lobby.game;

import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> cardDeck = new ArrayList<>(52);

    enum Suits {
        CLUBS,
        DIAMONDS,
        HEARTS,
        SPADES
    }

    enum Ranks{
        ACE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING
    }

    public Deck(){
        for (Ranks rank : Ranks.values()) {
            for (Suits suit : Suits.values() ) {
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
            System.out.println(card.getRank() + "    " + card.getSuit());
        }
    }

}
