package com.example.lobby.game;

import com.example.lobby.enums.Rank;
import com.example.lobby.enums.Suit;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Card {

    private Suit suit;
    private Rank rank;

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
