package com.company.hoteladministrator.service.mapper;

import com.company.hoteladministrator.api.mapper.IUserMapper;
import com.company.hoteladministrator.model.User;
import com.company.hoteladministrator.model.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper implements IUserMapper {

    private final ModelMapper mapper;

    @Autowired
    private UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public User toEntity(UserDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, User.class);
    }

    @Override
    public UserDto toDto(User entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, UserDto.class);
    }
}
