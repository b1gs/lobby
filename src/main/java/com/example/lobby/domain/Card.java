package com.example.lobby.domain;

import com.example.lobby.enums.Rank;
import com.example.lobby.enums.Suit;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Column
    @Id
    protected Long id;

    @Column
    @Enumerated(EnumType.STRING)
    protected Suit suit;

    @Column
    @Enumerated(EnumType.STRING)
    protected Rank rank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

}
