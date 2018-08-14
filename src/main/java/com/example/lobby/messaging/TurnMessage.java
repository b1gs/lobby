package com.example.lobby.messaging;

import com.example.lobby.enums.Rank;
import com.example.lobby.enums.Suit;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TurnMessage {

    private Rank rank;
    private Suit suit;
    private Long gameId;
    private Long playerId;

}
