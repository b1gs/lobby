package com.example.lobby.mapper;

import com.example.lobby.api.dto.RoomDto;
import com.example.lobby.domain.Room;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = { JpaEntityMapper.class })
public interface RoomMapper {

    @Mappings({
            @Mapping(source = "phone", target = "phoneNumber")
    })
    Room mapToEntity(RoomDto roomDto);

    RoomDto mapToDto(Room roomDto);

    List<Room> mapToEntities(List<RoomDto> roomDto);

    List<RoomDto> mapToDtos(List<Room> roomDto);

    //    @AfterMapping override if needed

}
