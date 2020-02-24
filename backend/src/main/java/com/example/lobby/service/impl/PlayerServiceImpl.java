package com.example.lobby.service.impl;

import com.example.lobby.domain.Player;
import com.example.lobby.domain.Room;
import com.example.lobby.repo.PlayerRepository;
import com.example.lobby.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player getPlayer(Long id) {
        return playerRepository.getOne(id);
    }

    @Override
    public Player getPlayerByUsername(String username) {
        return playerRepository.getPlayerByUsername(username);
    }

    @Override
    public Player create(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player update(Long id, Player player) {
        Player existingPlayer = playerRepository.getOne(id);
        if (existingPlayer == null) {
            throw new IllegalArgumentException("Player(id=" + id + ")" + "  NOT EXISTS");
        }
        player.setId(id);
        return playerRepository.save(player);
    }

    @Override
    public void delete(Long id) {
        playerRepository.delete(playerRepository.getOne(id));
    }

    @Override
    public void addPlayerToRoom(Player player, Room room) {
        if (room.getPlayers() != null && room.getPlayers().size() == 0) {
            room.setOwner(player);
        }
        if (room.getPlayers() != null && room.getPlayers().size() < room.getCapacity()) {
            player.setRoom(room);
            playerRepository.save(player);
        } else {
            throw new IllegalArgumentException("The Room(id=" + room.getId() + ") is FULL");
        }

    }

    @Override
    public void removePlayerFromRoom(Player player, Room room) {
        if (room.getPlayers().contains(player)) {
            player.setRoom(null);
            playerRepository.save(player);
        } else {
            throw new IllegalArgumentException("Pleyer(id=" + player.getId() + " is not belong to the Room(id=" + room.getId() + ")");
        }
    }
}
