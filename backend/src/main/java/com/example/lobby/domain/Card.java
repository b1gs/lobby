package com.example.lobby.domain;

import com.example.lobby.enums.Rank;
import com.example.lobby.enums.Suit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Card {

    @Enumerated(EnumType.STRING)
    @Column(name = "suit")
    protected Suit suit;

    @Enumerated(EnumType.STRING)
    @Column(name = "rank")
    protected Rank rank;

    @Column(name = "is_prikup")
    private boolean isPrikup;
}
