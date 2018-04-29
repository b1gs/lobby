package com.example.lobby.config;

import com.example.lobby.domain.SessionProfanity;
import com.example.lobby.event.PresenceEventListener;
import com.example.lobby.repo.ParticipantRepository;
import com.example.lobby.repo.PlayersRepository;
import com.example.lobby.util.ProfanityChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;

//import org.springframework.boot.actuate.endpoint.MessageMappingEndpoint;
//import org.springframework.boot.actuate.endpoint.WebSocketEndpoint;

@Configuration
@EnableConfigurationProperties(ChatProperties.class)
public class ChatConfig {

    @Autowired
    private ChatProperties chatProperties;

    @Bean
    @Description("Tracks user presence (join / leave) and broacasts it to all connected users")
    public PresenceEventListener presenceEventListener(SimpMessagingTemplate messagingTemplate, PlayersRepository playersRepository) {
        PresenceEventListener presence = new PresenceEventListener(messagingTemplate, participantRepository(),playersRepository);
        presence.setLoginDestination(chatProperties.getDestinations().getLogin());
        presence.setLogoutDestination(chatProperties.getDestinations().getLogout());
        return presence;
    }

    @Bean
    @Description("Keeps connected users")
    public ParticipantRepository participantRepository() {
        return new ParticipantRepository();
    }

    @Bean
    @Scope(value = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Description("Keeps track of the level of profanity of a websocket session")
    public SessionProfanity sessionProfanity() {
        return new SessionProfanity(chatProperties.getMaxProfanityLevel());
    }

    @Bean
    @Description("Utility class to check the number of profanities and filter them")
    public ProfanityChecker profanityFilter() {
        ProfanityChecker checker = new ProfanityChecker();
        checker.setProfanities(chatProperties.getDisallowedWords());
        return checker;
    }

    @Bean
    public SessionRepository<ExpiringSession> sessionRepository(){
        MapSessionRepository repository = new MapSessionRepository();
        repository.setDefaultMaxInactiveInterval(60);
        return repository;
    }

//    @Bean
//    @Description("Spring Actuator endpoint to expose WebSocket stats")
//    public WebSocketEndpoint websocketEndpoint(WebSocketMessageBrokerStats stats) {
//        return new WebSocketEndpoint(stats);
//    }

//    @Bean
//    @Description("Spring Actuator endpoint to expose WebSocket message mappings")
//    public MessageMappingEndpoint messageMappingEndpoint() {
//        return new MessageMappingEndpoint();
//    }

}
