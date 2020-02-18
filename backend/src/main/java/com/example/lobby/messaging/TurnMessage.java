package com.example.lobby.messaging;

import com.example.lobby.domain.Card;
import com.example.lobby.enums.Rank;
import com.example.lobby.enums.Suit;
import com.example.lobby.enums.TurnType;
import lombok.Getter;
import lombok.Setter;
import org.omg.CORBA.Object;

import java.util.Set;


@Getter
@Setter
public class TurnMessage {

    private Set<Card> cards;
    private Long gameId;
    private Long playerId;
    private TurnType turnType;

}
