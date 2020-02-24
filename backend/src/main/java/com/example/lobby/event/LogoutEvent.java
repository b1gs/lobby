package com.example.lobby.event;

public class LogoutEvent {

    private String username;
    private Long roomId;

    public LogoutEvent(String username, Long roomId) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
