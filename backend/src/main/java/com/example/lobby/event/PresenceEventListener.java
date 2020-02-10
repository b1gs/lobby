package com.example.lobby.event;

import com.example.lobby.domain.Player;
import com.example.lobby.repo.ParticipantRepository;
import com.example.lobby.repo.PlayerRepository;
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

    private PlayerRepository playerRepository;

    private SimpMessagingTemplate messagingTemplate;

    private String loginDestination;

    private String logoutDestination;

    private static String TOPIC = "/topic/";

    public PresenceEventListener(SimpMessagingTemplate messagingTemplate, ParticipantRepository participantRepository , PlayerRepository playerRepository) {
        this.messagingTemplate = messagingTemplate;
        this.participantRepository = participantRepository;
        this.playerRepository = playerRepository;
    }

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String username = headers.getUser().getName();

        Player player = playerRepository.getPlayerByUsername(username);
        if (player.getRoom() == null ){
            LOGGER.error("Room is null!!! Check THIS OUT! It must be not null" );
        }

        LoginEvent loginEvent = new LoginEvent(username,player.getRoom().getId() );

        messagingTemplate.convertAndSend(TOPIC + player.getRoom().getId()+loginDestination, loginEvent);

        // We store the session as we need to be idempotent in the disconnect event processing
        participantRepository.add(headers.getSessionId(), loginEvent
        );
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        removePlayerFromRoom(event);
        Optional.ofNullable(participantRepository.getParticipant(event.getSessionId()))
                .ifPresent(login -> {
                    messagingTemplate.convertAndSend(TOPIC+logoutDestination, new LogoutEvent(login.getUsername(),login.getRoomId()));
                    participantRepository.removeParticipant(event.getSessionId());
                });
    }

    public void setLoginDestination(String loginDestination) {
        this.loginDestination = loginDestination;
    }

    public void setLogoutDestination(String logoutDestination) {
        this.logoutDestination = logoutDestination;
    }

    private void removePlayerFromRoom(SessionDisconnectEvent event){
        LoginEvent participant = participantRepository.getParticipant(event.getSessionId());
        Player player = playerRepository.getPlayerByUsername(participant.getUsername());
        player.setRoom(null);
        playerRepository.save(player);
    }
}
