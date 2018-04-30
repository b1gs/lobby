package com.example.lobby.game;

import com.example.lobby.domain.Room;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrikypaThread implements Runnable {

    private Room room;

    @Override
    public void run() {

    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
