package com.example.lobby.repo;

import com.example.lobby.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long>{

    Game findFirstByRoomId(Long roomId);

}
