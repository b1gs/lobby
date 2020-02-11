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
public class Card {

    @Enumerated(EnumType.STRING)
    protected Suit suit;

    @Enumerated(EnumType.STRING)
    protected Rank rank;

}
