package com.example.lobby.service.impl;

import com.example.lobby.domain.Player;
import com.example.lobby.domain.Room;
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
    public Player getPlayerByUsername(String username) {
        return playersRepository.getPlayerByUsername(username);
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

    @Override
    public void addPlayerToRoom(Player player, Room room) {
        if (room.getPlayers()!=null && room.getPlayers().size()==0){
            room.setOwner(player);
        }
        if(room.getPlayers()!=null && room.getPlayers().size() < room.getCapacity()){
            player.setRoom(room);
            playersRepository.save(player);
        }else {
            throw new IllegalArgumentException("The Room(id="+room.getId()+") is FULL");
        }

    }

    @Override
    public void removePlayerFromRoom(Player player, Room room) {
        if (room.getPlayers().contains(player)){
            player.setRoom(null);
            playersRepository.save(player);
        }else {
            throw new IllegalArgumentException("Pleyer(id="+player.getId() +  " is not belong to the Room(id="+room.getId() + ")");
        }
    }
}
