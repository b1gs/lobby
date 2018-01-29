package com.example.lobby.mapper;

import com.example.lobby.api.dto.PlayerDto;
import com.example.lobby.domain.Player;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {JpaEntityMapper.class})
public interface PlayerMapper {

    Player mapToEntity(PlayerDto playerDto);

    PlayerDto mapToDto(Player player);


    List<Player> mapToEntities(List<PlayerDto> playerDtos);

    List<PlayerDto> mapToDtos(List<Player> players);
}
