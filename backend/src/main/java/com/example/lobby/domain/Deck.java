package com.example.lobby.domain;

import com.example.lobby.enums.Rank;
import com.example.lobby.enums.Suit;
import lombok.experimental.UtilityClass;

import java.util.LinkedList;
import java.util.List;

@UtilityClass
public class Deck {

    private static List<Card> deck;

    static {
        deck = new LinkedList<>();
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                deck.add(new Card(suit, rank,false));
            }
        }
    }

    public static List<Card> getDeck() {
        return deck;
    }

}
