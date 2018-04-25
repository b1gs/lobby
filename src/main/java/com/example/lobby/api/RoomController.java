package com.example.lobby.api;


import com.example.lobby.api.dto.RoomDto;
import com.example.lobby.domain.Player;
import com.example.lobby.domain.Room;
import com.example.lobby.labels.messages.ApiResponseMessages;
import com.example.lobby.mapper.RoomMapper;
import com.example.lobby.service.PlayerService;
import com.example.lobby.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.HttpURLConnection;
import java.util.List;


@RestController
@RequestMapping(value = "/v1/room", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "room", description = "Endpoints for managing rooms")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {


    private final RoomMapper roomMapper;
    private final RoomService roomService;
    private final PlayerService playerService;


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            nickname = "getRoom",
            value = "get rooms",
            notes = "get rooms",
            response = RoomDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = ApiResponseMessages.OK),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = ApiResponseMessages.INTERNAL_SERVER_ERROR)
    })
    public List<RoomDto> getAllRooms(){
        return roomMapper.mapToDtos(roomService.getAllRooms());
    }

    @GetMapping("/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            nickname = "getRoom",
            value = "get room",
            notes = "Get room",
            response = RoomDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = ApiResponseMessages.OK),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = ApiResponseMessages.INTERNAL_SERVER_ERROR)
    })
    public RoomDto geRoom(@Valid @PathVariable Long roomId){
        return roomMapper.mapToDto(roomService.getRoom(roomId));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            nickname = "createRoom",
            value = "creates room",
            notes = "Creates room",
            response = RoomDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = ApiResponseMessages.OK),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = ApiResponseMessages.INTERNAL_SERVER_ERROR)
    })
    public RoomDto create(@Valid @RequestBody RoomDto roomDto ) {
        Room room = roomMapper.mapToEntity( roomDto);
        return roomMapper.mapToDto(roomService.create(room));
    }


    @PutMapping("/{roomId}")
    @ApiOperation(
            nickname = "updateRoom",
            value = "updates room",
            notes = "Updates room",
            response = RoomDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = ApiResponseMessages.OK),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = ApiResponseMessages.NOT_FOUND),
//            @ApiResponse(code = HttpURLConnection.HTTP_CONFLICT, message = ApiResponseMessages.CONFLICT_OPTIMISTIC_LOCK),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = ApiResponseMessages.INTERNAL_SERVER_ERROR)
    })
    public RoomDto update(@PathVariable("roomId") Long roomId,
                             @Valid @RequestBody RoomDto roomUpdateDto) throws NotFoundException {
        Room roomUpdate = roomMapper.mapToEntity(roomUpdateDto);
        return roomMapper.mapToDto(roomService.update(roomId, roomUpdate));
    }

    @PostMapping("/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            nickname = "deleteRoom",
            value = "deletes room",
            notes = "deletes room",
            response = RoomDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = ApiResponseMessages.OK),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = ApiResponseMessages.NOT_FOUND),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = ApiResponseMessages.INTERNAL_SERVER_ERROR)
    })
    public void delete(@Valid @PathVariable Long roomId ) {
        roomService.delete(roomId);
    }

    @PutMapping("/{roomId}/addPlayer/{playerId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            nickname = "deleteRoom",
            value = "delete player from the room",
            notes = "delete player from the room",
            response = RoomDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = ApiResponseMessages.OK),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = ApiResponseMessages.NOT_FOUND),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = ApiResponseMessages.INTERNAL_SERVER_ERROR)
    })
    public void addPlayerToRoom(@Valid @PathVariable Long roomId , @Valid @PathVariable Long playerId  ) {

        Player player = playerService.getPlayer(playerId);
        Room room = roomService.getRoom(roomId);
        if (player != null && room!=null){
            playerService.addPlayerToRoom(player , room);
        }else{
            throw new IllegalArgumentException("Player or room is null");
        }


    }

    @PostMapping("/{roomId}/removePlayer/{playerId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            nickname = "deleteRoom",
            value = "delete player from the room",
            notes = "delete player from the room",
            response = RoomDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = ApiResponseMessages.OK),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = ApiResponseMessages.NOT_FOUND),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = ApiResponseMessages.INTERNAL_SERVER_ERROR)
    })
    public void removePlayerFromRoom(@Valid @PathVariable Long roomId , @Valid @PathVariable Long playerId  ) {

        Player player = playerService.getPlayer(playerId);
        Room room = roomService.getRoom(roomId);
        if (player != null && room!=null){
            playerService.removePlayerFromRoom(player , room);
        }else{
            throw new IllegalArgumentException("Player or room is null");
        }


    }

}
