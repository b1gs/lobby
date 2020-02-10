package com.example.lobby.repo;

import com.example.lobby.domain.Game;
import com.example.lobby.domain.Turn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnRepository extends JpaRepository<Turn,Long>{

    Long countTurnsByGame(Game game);


}
