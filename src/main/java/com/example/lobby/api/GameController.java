package com.example.lobby.api;

import com.example.lobby.domain.ChatMessage;
import com.example.lobby.domain.Player;
import com.example.lobby.domain.Room;
import com.example.lobby.service.CardService;
import com.example.lobby.service.PlayerService;
import com.example.lobby.service.RoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class GameController {


    private final PlayerService playerService;
    private final RoomService roomService;
    private final CardService cardService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ObjectMapper objectMapper;

    @MessageMapping("{roomId}/startGame")
    public void startGame(@Payload ChatMessage message, Principal principal, @DestinationVariable Long roomId ) throws JsonProcessingException {
        Room room = roomService.getRoom(roomId);
        if (!principal.getName().equals(room.getOwner().getUsername())){
            throw new IllegalArgumentException("You are not the owner");
        }
        for(Player p : room.getPlayers()){
            if (!p.isReady()){
                throw new IllegalArgumentException("Not all players is ready");
            }
        }
        Set<Player> players = cardService.handOverCards(room.getPlayers());
        for (Player p : players ){
            message.setMessage(objectMapper.writeValueAsString(p.getPlayerCards()));
            simpMessagingTemplate.convertAndSend("/user/" + p.getUsername() + "/exchange/amq.direct/chat.message", message);
        }
    }

}