package com.company.mapper;

import com.company.api.mapper.IRoomMapper;
import com.company.dto.RoomDto;
import com.company.model.Room;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RoomMapper implements IRoomMapper {

    private ModelMapper mapper;

    @Autowired
    private RoomMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Room toEntity(RoomDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Room.class);
    }

    @Override
    public RoomDto toDto(Room entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, RoomDto.class);
    }
}
