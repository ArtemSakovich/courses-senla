package com.company.hoteladministrator.service.mapper;

import com.company.hoteladministrator.api.mapper.IMaintenanceMapper;
import com.company.hoteladministrator.model.Maintenance;
import com.company.hoteladministrator.model.dto.MaintenanceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MaintenanceMapper implements IMaintenanceMapper {

    private ModelMapper mapper;

    @Autowired
    private MaintenanceMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Maintenance toEntity(MaintenanceDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Maintenance.class);
    }

    @Override
    public MaintenanceDto toDto(Maintenance entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, MaintenanceDto.class);
    }
}
