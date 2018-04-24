package com.example.lobby.api.dto;


import com.example.lobby.domain.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoomDto extends BaseDto {


    @NotNull
    private Long id;

    @NotNull
    @Size( min = 1 , max = 30 )
    private String roomName;


    @JsonIgnore
    private Set<Player> players = new HashSet<>(6);


}


