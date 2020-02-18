package com.example.lobby.messaging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {

    private String username;
    private String message;
    private Integer currentTurnPlayerNumber;


    @Override
    public String toString() {
        return "ChatMessage [user=" + username + ", message=" + message + "]";
    }

}
