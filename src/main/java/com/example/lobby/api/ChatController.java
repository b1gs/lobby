package com.example.lobby.api;

import com.example.lobby.domain.ChatMessage;
import com.example.lobby.domain.SessionProfanity;
import com.example.lobby.event.LoginEvent;
import com.example.lobby.exception.TooMuchProfanityException;
import com.example.lobby.repo.ParticipantRepository;
import com.example.lobby.util.ProfanityChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by ovolkovskyi on 24.01.2018.
 */
@Controller
public class ChatController {

    @Autowired
    private ProfanityChecker profanityFilter;

    @Autowired private SessionProfanity profanity;

    @Autowired private ParticipantRepository participantRepository;

    @Autowired private SimpMessagingTemplate simpMessagingTemplate;


    @SubscribeMapping("{roomId}/chat.participants")
    public Collection<LoginEvent> retrieveParticipants(@DestinationVariable Long roomId) {
        Collection<LoginEvent> participants = participantRepository.getActiveSessions().values();
        return participants.stream().filter(participant ->participant.getRoomId().equals(roomId)).collect(Collectors.toList());
    }

    @MessageMapping("{roomId}/chat.message/")
    @SendTo("/topic/{roomId}/chat.message")
    public ChatMessage filterMessage(@Payload ChatMessage message, Principal principal, @DestinationVariable String roomId ) {
        checkProfanityAndSanitize(message);
        System.out.println("RoomId= " + roomId);
        message.setUsername(principal.getName());

        return message;
    }

    @MessageMapping("/chat.private.{username}")
    public void filterPrivateMessage(@Payload ChatMessage message, @DestinationVariable("username") String username, Principal principal) {
        checkProfanityAndSanitize(message);

        message.setUsername(principal.getName());

        simpMessagingTemplate.convertAndSend("/user/" + username + "/exchange/amq.direct/chat.message", message);
    }

    private void checkProfanityAndSanitize(ChatMessage message) {
        long profanityLevel = profanityFilter.getMessageProfanity(message.getMessage());
        profanity.increment(profanityLevel);
        message.setMessage(profanityFilter.filter(message.getMessage()));
    }

    @MessageExceptionHandler
    @SendToUser(value = "/exchange/amq.direct/errors", broadcast = false)
    public String handleProfanity(TooMuchProfanityException e) {
        return e.getMessage();
    }

}
