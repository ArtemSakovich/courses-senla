package com.company.hoteladministrator.service.mapper;

import com.company.hoteladministrator.api.mapper.IGuestMapper;
import com.company.hoteladministrator.model.Guest;
import com.company.hoteladministrator.model.dto.GuestDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GuestMapper implements IGuestMapper {

    private ModelMapper mapper;

    @Autowired
    private GuestMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Guest toEntity(GuestDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Guest.class);
    }

    @Override
    public GuestDto toDto(Guest entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, GuestDto.class);
    }
}
