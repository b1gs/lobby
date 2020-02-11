package com.example.lobby.service.impl;

import com.example.lobby.domain.Card;
import com.example.lobby.domain.Game;
import com.example.lobby.domain.Player;
import com.example.lobby.domain.Room;
import com.example.lobby.enums.GameState;
import com.example.lobby.enums.Rank;
import com.example.lobby.enums.Suit;
import com.example.lobby.repo.GameRepository;
import com.example.lobby.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@Transactional
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Override
    public Game create(Set<Player> players, Room room) {
        Player firstPlayer = determineFirstPlayer(players);
        Game game = new Game();
        game.setCurrentTurnPlayerNumber(firstPlayer.getId());
        game.setRoom(room);
        game.setGameStatus(GameState.IN_PROGRESS);
        game.setTurnNumber(0L);
        game.setPlayerTurnMap(preparePlayerTurnMap(firstPlayer, players));
        return gameRepository.save(game);
    }

    private Player determineFirstPlayer(Set<Player> players) {
        for (Player player : players) {
            for (Card card : player.getPlayerCards()) {
                if (card.getSuit() == Suit.HEARTS && card.getRank() == Rank.TWO) {
                    return player;
                }
            }
        }
        return null;
    }

    private Map<Long, Player> preparePlayerTurnMap(Player firstPlayer, Set<Player> players) {
        Map<Long, Player> playerTurnMap = new HashMap<>();
        long ctr = 1L;
        playerTurnMap.put(ctr++, firstPlayer);
        for (Player player : players) {
            if (player.equals(firstPlayer)) {
                playerTurnMap.put(ctr++, player);
            }
        }
        return playerTurnMap;
    }
}
