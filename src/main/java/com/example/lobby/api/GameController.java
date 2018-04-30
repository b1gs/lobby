package com.example.lobby.api;

import com.example.lobby.domain.ChatMessage;
import com.example.lobby.domain.Player;
import com.example.lobby.domain.Room;
import com.example.lobby.repo.PlayersRepository;
import com.example.lobby.repo.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class GameController {


    private final PlayersRepository playersRepository;
    private final RoomRepository roomRepository;

    @MessageMapping("{roomId}/startGame")
    @SendTo("/topic/{roomId}/startGame")
    public ChatMessage filterMessage(@Payload ChatMessage message, Principal principal, @DestinationVariable Long roomId ) {
        Room room = roomRepository.getOne(roomId);
        for(Player p : room.getPlayers()){
            if (!p.isReady()){
                throw new IllegalArgumentException("Not all players is ready");
            }
        }
        message.setUsername(principal.getName());
        return message;
    }



}
