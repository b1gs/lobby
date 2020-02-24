package com.example.lobby.service.impl;

import com.example.lobby.domain.Card;
import com.example.lobby.domain.Game;
import com.example.lobby.domain.Player;
import com.example.lobby.domain.Room;
import com.example.lobby.enums.GameState;
import com.example.lobby.enums.Rank;
import com.example.lobby.enums.Suit;
import com.example.lobby.messaging.TurnMessage;
import com.example.lobby.repo.GameRepository;
import com.example.lobby.service.CardService;
import com.example.lobby.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@Transactional
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final CardService cardService;

    @Override
    public Game create(Set<Player> players, Room room) {
        Player firstPlayer = determineFirstPlayer(players);
        Game game = new Game();
        game.setCurrentTurnPlayerNumber(1);
        game.setRoom(room);
        game.setGameStatus(GameState.IN_PROGRESS);
        game.setTurnNumber(0L);
        game.setPlayerTurnMap(
                cardService.handOverCards(preparePlayerTurnMap(firstPlayer, players)));
        return gameRepository.save(game);
    }

    @Override
    public boolean isPlayerTurn(Long roomId, TurnMessage turnMessage) {
        Game game = gameRepository.findFirstByRoomId(roomId);
        Player currentTurnPlayer = game.getCurrentTurnPlayer();
        return currentTurnPlayer.getId().equals(turnMessage.getPlayerId());
    }

    @Override
    public boolean makeTurn(TurnMessage turnMessage) {
        Game game = gameRepository.getOne(turnMessage.getGameId());

        Set<Card> turnCards = turnMessage.getCards();
        Player currentTurnPlayer = game.getCurrentTurnPlayer();
        Set<Card> currentTurnPlayerCards = currentTurnPlayer.getPlayerCards();

        addCardsToGameBank(game, turnCards);
        removePlayerCards(currentTurnPlayerCards, currentTurnPlayerCards);

        takePrikupOrRemovePlayerWithoutCards(game, currentTurnPlayer);

        setTurnToNextPlayer(game);

        gameRepository.save(game);

        return isGameFinished(game);

    }

    private void takePrikupOrRemovePlayerWithoutCards(Game game, Player currentTurnPlayer) {
        Set<Card> playerCards = currentTurnPlayer.getPlayerCards();
        if (playerCards.isEmpty()) {
            if (currentTurnPlayer.getPlayerPrikup().isEmpty()) {
                removePlayerWithoutCards(playerCards, game);
            } else {
                playerCards.addAll(currentTurnPlayer.getPlayerPrikup());
            }
        }
    }

    @Override
    public void pickUpCards(TurnMessage turnMessage) {
        Game game = gameRepository.getOne(turnMessage.getGameId());
        int bankSize = game.getBank().size();
        List<Card> cardsToPickUp = game.getBank().subList(bankSize - 5, bankSize - 1);

        game.getCurrentTurnPlayer().getPlayerCards().addAll(cardsToPickUp);

        gameRepository.save(game);
    }

    private void removePlayerWithoutCards(Set<Card> currentTurnPlayerCards, Game game) {
        Map<Integer, Player> playerTurnMap = game.getPlayerTurnMap();
        if (currentTurnPlayerCards.isEmpty()) {
            playerTurnMap.remove(game.getCurrentTurnPlayerNumber());
        }
    }

    private void setTurnToNextPlayer(Game game) {
        Integer nextPlayerNumber = game.getCurrentTurnPlayerNumber() + 1;
        if (nextPlayerNumber > game.getPlayerTurnMap().keySet().size()) {
            nextPlayerNumber = 0;
        }
        game.setCurrentTurnPlayerNumber(nextPlayerNumber);
    }

    private void addCardsToGameBank(Game game, Set<Card> cards) {
        game.getBank().addAll(cards);
    }

    private void removePlayerCards(Set<Card> playerCards, Set<Card> turnCards) {
        playerCards.removeAll(turnCards);
    }

    private boolean isGameFinished(Game game) {
        Collection<Player> gamePlayers = game.getPlayerTurnMap().values();
        if (gamePlayers.isEmpty()) {
            return true;
        }
        return false;
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

    private Map<Integer, Player> preparePlayerTurnMap(Player firstPlayer, Set<Player> players) {
        Map<Integer, Player> playerTurnMap = new HashMap<>();
        int ctr = 1;
        playerTurnMap.put(ctr++, firstPlayer);
        for (Player player : players) {
            if (player.equals(firstPlayer)) {
                playerTurnMap.put(ctr++, player);
            }
        }
        return playerTurnMap;
    }
}
