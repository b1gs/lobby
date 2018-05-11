package com.example.lobby.api;

import com.example.lobby.api.dto.RoomDto;
import com.example.lobby.domain.Player;
import com.example.lobby.domain.Room;
import com.example.lobby.game.Card;
import com.example.lobby.game.Deck;
import com.example.lobby.labels.messages.ApiResponseMessages;
import com.example.lobby.mapper.RoomMapper;
import com.example.lobby.repo.PlayersRepository;
import com.example.lobby.repo.RoomRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/v1/game", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "game", description = "Endpoints for managing games")
@RequiredArgsConstructor
public class GameController {


    private final PlayersRepository playersRepository;
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    //@MessageMapping("{roomId}/startGame")
    //@SendTo("/topic/{roomId}/startGame")
    @GetMapping("/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            nickname = "startGame",
            value = "start game",
            notes = "start game",
            response = RoomDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = ApiResponseMessages.OK),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = ApiResponseMessages.INTERNAL_SERVER_ERROR)
    })
    public RoomDto startGame(@PathVariable Long roomId) {//@Payload ChatMessage message, Principal principal, @DestinationVariable Long roomId ) {
        Room room = roomRepository.getOne(roomId);
        for(Player p : room.getPlayers()){
            if (!p.isReady()){
                throw new IllegalArgumentException("Not all players is ready");
            }
        }
        handOverCards(room.getPlayers());
        //message.setUsername(principal.getName());
        return roomMapper.mapToDto(room);
    }


    private void handOverCards( Set<Player> players){
        Deck deck = new Deck();
        int currentPlayer = 0;
        List<Set<Card>> playersCards = new ArrayList(players.size());
        initCardArray(playersCards, players.size());
        while ( deck.hasNext()){
            playersCards.get(currentPlayer).add(deck.getNext());
            currentPlayer++;
            if (currentPlayer > players.size() - 1){
                currentPlayer = 0;
            }
        }
        currentPlayer = 0;
        for(Player p : players){
            System.out.println("Players card before set:  " + p.getPlayerCards());
            p.setPlayerCards(playersCards.get(currentPlayer++));
            playersRepository.save(p);
        }
        for (Set<Card> cards : playersCards){
            System.out.println(cards);
        }
    }

    private void initCardArray(List<Set<Card>> playersCards, int numberOfPlayers){
        for(int i = 0; i < numberOfPlayers; i++){
            playersCards.add(new HashSet<Card>());
        }
    }

}