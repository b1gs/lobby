package com.example.lobby.repo;

import com.example.lobby.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player getPlayerByUsername(String username);

}
