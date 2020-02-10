package com.example.lobby.event;

import java.util.Date;

public class LoginEvent {

    private String username;
    private Date time;
    private Long roomId;

    public LoginEvent(String username, Long roomId) {
        this.username = username;
        this.roomId = roomId;
        time = new Date();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
