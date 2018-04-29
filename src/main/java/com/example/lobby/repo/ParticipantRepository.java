package com.example.lobby.repo;

import com.example.lobby.event.LoginEvent;

import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParticipantRepository {

    private Map<Long, HashSet<String>> roomsSessions = new ConcurrentHashMap<>();
    private Map<String, LoginEvent> activeSessions = new ConcurrentHashMap<>();

    public void add(String sessionId, LoginEvent event, Long roomId) {
        if (!roomsSessions.containsKey(roomId)){
            HashSet<String> roomPlayers = new HashSet<>();
            roomPlayers.add(sessionId);
            roomsSessions.put(roomId, roomPlayers);
        }else {
            roomsSessions.get(roomId).add(sessionId);
        }

        activeSessions.put(sessionId, event);
    }

    public LoginEvent getParticipant(String sessionId) {
        return activeSessions.get(sessionId);
    }

    public void removeParticipant(String sessionId ,Long roomId ) {
        if (roomsSessions.get(roomId)!=null){
            roomsSessions.get(roomId).remove(sessionId);
        }
        activeSessions.remove(sessionId);
    }

    public Map<String, LoginEvent> getActiveSessions() {
        return activeSessions;
    }

    public void setActiveSessions(Map<String, LoginEvent> activeSessions) {
        this.activeSessions = activeSessions;
    }

}
