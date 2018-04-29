package com.example.lobby.event;

import com.example.lobby.domain.Player;
import com.example.lobby.repo.ParticipantRepository;
import com.example.lobby.repo.PlayersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Optional;

public class PresenceEventListener {

    private static Logger LOGGER = LoggerFactory.getLogger(PresenceEventListener.class);

    private ParticipantRepository participantRepository;

    private PlayersRepository playersRepository;

    private SimpMessagingTemplate messagingTemplate;

    private String loginDestination;

    private String logoutDestination;

    private static String TOPIC = "/topic/";

    public PresenceEventListener(SimpMessagingTemplate messagingTemplate, ParticipantRepository participantRepository , PlayersRepository playersRepository) {
        this.messagingTemplate = messagingTemplate;
        this.participantRepository = participantRepository;
        this.playersRepository = playersRepository;
    }

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String username = headers.getUser().getName();

        Player player = playersRepository.getPlayerByUsername(username);
        if (player.getRoom() == null ){
            LOGGER.error("Room is null!!! Check THIS OUT! It must be not null" );
        }

        LoginEvent loginEvent = new LoginEvent(username);

        messagingTemplate.convertAndSend(TOPIC+ player.getRoom().getId()+loginDestination, loginEvent);

        // We store the session as we need to be idempotent in the disconnect event processing
        participantRepository.add(headers.getSessionId(), loginEvent, player.getRoom().getId());
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String username = headers.getUser().getName();

        Player player = playersRepository.getPlayerByUsername(username);
        if (player.getRoom() == null ){
            LOGGER.error("Room is null!!! Check THIS OUT! It must be not null" );
        }
        Optional.ofNullable(participantRepository.getParticipant(event.getSessionId()))
                .ifPresent(login -> {
                    messagingTemplate.convertAndSend(TOPIC+logoutDestination, new LogoutEvent(login.getUsername()));
                    participantRepository.removeParticipant(event.getSessionId(), player.getRoom().getId());
                });
    }

    public void setLoginDestination(String loginDestination) {
        this.loginDestination = loginDestination;
    }

    public void setLogoutDestination(String logoutDestination) {
        this.logoutDestination = logoutDestination;
    }

}
