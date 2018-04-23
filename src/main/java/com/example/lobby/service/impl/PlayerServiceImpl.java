package com.example.lobby.service.impl;

import com.example.lobby.domain.Player;
import com.example.lobby.repo.PlayersRepository;
import com.example.lobby.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PlayerServiceImpl  implements PlayerService {

    private final PlayersRepository playersRepository;

    @Override
    public List<Player> getAllPlayers() {
        return playersRepository.findAll();
    }

    @Override
    public Player getPlayer(Long id) {
        return playersRepository.getOne(id);
    }

    @Override
    public Player create(Player player) {
        return playersRepository.save(player);
    }

    @Override
    public Player update(Long id, Player player) {
        Player existingPlayer = playersRepository.getOne(id);
        if (existingPlayer == null ){
            throw new IllegalArgumentException("Player(id="+id+")" + "  NOT EXISTS");
        }
        player.setId(id);
        return playersRepository.save(player);
    }

    @Override
    public void delete(Long id) {
        playersRepository.delete(playersRepository.getOne(id));
    }

}
