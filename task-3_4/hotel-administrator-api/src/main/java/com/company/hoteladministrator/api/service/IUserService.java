package com.company.hoteladministrator.api.service;

import com.company.hoteladministrator.model.User;
import com.company.hoteladministrator.model.dto.UserDto;

import java.util.List;

public interface IUserService {

    UserDto addUser(UserDto userDto);

    List<UserDto> getAllUsers();

    User getUserByUsername(String username);

    UserDto getById(Long id);

    void deleteUser(Long id);
}
