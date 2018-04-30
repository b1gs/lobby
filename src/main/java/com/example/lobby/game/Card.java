package com.example.lobby.game;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Card {

    private Deck.Suits suit;
    private Deck.Ranks rank;

    public Deck.Suits getSuit() {
        return suit;
    }

    public void setSuit(Deck.Suits suit) {
        this.suit = suit;
    }

    public Deck.Ranks getRank() {
        return rank;
    }

    public void setRank(Deck.Ranks rank) {
        this.rank = rank;
    }
}
