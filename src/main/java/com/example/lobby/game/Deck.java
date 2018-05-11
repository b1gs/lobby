package com.example.lobby.game;

import com.example.lobby.enums.Rank;
import com.example.lobby.enums.Suit;

public class Deck {

    private Card [] cardDeck = new Card[52];

    private int numberOfCardsLeft = 52;

    public Deck(){
        int ctr =0;
        for (Suit suit : Suit.values() ) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(rank, suit);
                cardDeck[ctr++]= card;
            }
        }

        for (int i = 0; i < 52; i++) {
            int r = i + (int) (Math.random() * (52-i));
            Card temp = cardDeck[r];
            cardDeck[r] = cardDeck[i];
            cardDeck[i] = temp;
        }
        for(Card card : cardDeck){
            System.out.println( card.getRank() + "    " + card.getSuit() );
        }
    }


    public Card getNext(){
       return cardDeck[--numberOfCardsLeft];
    }

    public boolean hasNext(){
        return numberOfCardsLeft > 0 ;
    }
}
