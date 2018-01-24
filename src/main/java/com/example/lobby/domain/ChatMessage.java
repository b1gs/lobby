package com.example.lobby.domain;

/**
 * Created by ovolkovskyi on 24.01.2018.
 */
public class ChatMessage {

    private String username;
    private String message;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ChatMessage [user=" + username + ", message=" + message + "]";
    }
}
