package com.example.lobby.repo;

import com.example.lobby.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayersRepository extends JpaRepository<Player , Long> {
}