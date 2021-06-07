package com.company.hoteladministrator.service.mapper;

import com.company.hoteladministrator.api.mapper.IRoleMapper;
import com.company.hoteladministrator.model.Role;
import com.company.hoteladministrator.model.dto.RoleDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RoleMapper implements IRoleMapper {

    private final ModelMapper mapper;

    @Autowired
    private RoleMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Role toEntity(RoleDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Role.class);
    }

    @Override
    public RoleDto toDto(Role entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, RoleDto.class);
    }
}
