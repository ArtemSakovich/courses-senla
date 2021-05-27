package com.company.hoteladministrator.service.mapper;

import com.company.hoteladministrator.api.mapper.IRoomMapper;
import com.company.hoteladministrator.model.Room;
import com.company.hoteladministrator.model.dto.RoomDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RoomMapper implements IRoomMapper {

    private final ModelMapper mapper;

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
