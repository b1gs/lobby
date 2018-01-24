package com.example.lobby.event;

/**
 * Created by ovolkovskyi on 24.01.2018.
 */
public class LogoutEvent {

    private String username;

    public LogoutEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
