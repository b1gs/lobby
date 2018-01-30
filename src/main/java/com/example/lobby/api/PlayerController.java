package com.example.lobby.api;

import com.example.lobby.api.dto.PlayerDto;
import com.example.lobby.domain.Player;
import com.example.lobby.labels.messages.ApiResponseMessages;
import com.example.lobby.mapper.PlayerMapper;
import com.example.lobby.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.HttpURLConnection;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/player", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "player", description = "Endpoints for managing players")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlayerController {

    private PlayerMapper playerMapper;
    private PlayerService playerService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            nickname = "getPlayers",
            value = "get players",
            notes = "get players",
            response = PlayerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = ApiResponseMessages.OK),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = ApiResponseMessages.INTERNAL_SERVER_ERROR)
    })
    public List<PlayerDto> getAllPlayers(){
        return playerMapper.mapToDtos(playerService.getAllPlayers());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            nickname = "getPlayer",
            value = "get player",
            notes = "get player",
            response = PlayerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = ApiResponseMessages.OK),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = ApiResponseMessages.INTERNAL_SERVER_ERROR)
    })
    public PlayerDto getPlayer(@Valid @PathVariable Long id ){
        return playerMapper.mapToDto(playerService.getPlayer(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            nickname = "createPlayer",
            value = "creates player",
            notes = "creates player",
            response = PlayerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = ApiResponseMessages.OK),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = ApiResponseMessages.INTERNAL_SERVER_ERROR)
    })
    public PlayerDto create(@Valid @RequestBody PlayerDto playerDto){
        Player player = playerMapper.mapToEntity(playerDto);
        return playerMapper.mapToDto(playerService.create(player));
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            nickname = "updatePlayer",
            value = "updates player",
            notes = "updates player",
            response = PlayerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = ApiResponseMessages.OK),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = ApiResponseMessages.INTERNAL_SERVER_ERROR)
    })
    public PlayerDto update(@Valid @PathVariable Long playerId , @Valid @RequestBody PlayerDto playerUpdateDto){
        Player playerUpdate = playerMapper.mapToEntity(playerUpdateDto);
        return playerMapper.mapToDto(playerService.update(playerId, playerUpdate));
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            nickname = "deletePlayer",
            value = "delete player",
            notes = "delete player",
            response = PlayerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = ApiResponseMessages.OK),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = ApiResponseMessages.INTERNAL_SERVER_ERROR)
    })
    public void delete(@Valid @PathVariable Long id ){
        playerService.delete(id);
    }



}
