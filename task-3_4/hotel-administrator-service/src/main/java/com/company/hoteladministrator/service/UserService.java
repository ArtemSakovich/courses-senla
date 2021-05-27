package com.company.hoteladministrator.service;

import com.company.hoteladministrator.api.dao.IRoleDao;
import com.company.hoteladministrator.api.dao.IUserDao;
import com.company.hoteladministrator.api.mapper.IUserMapper;
import com.company.hoteladministrator.api.service.IUserService;
import com.company.hoteladministrator.model.Role;
import com.company.hoteladministrator.model.User;
import com.company.hoteladministrator.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements IUserService {

    private final IUserDao userDao;
    private final IRoleDao roleDao;
    private final PasswordEncoder passwordEncoder;
    private final IUserMapper userMapper;
    @Value("${roleUser:ROLE_USER}")
    private String roleUser;

    @Autowired
    public UserService(IUserDao userDao, IRoleDao roleDao, PasswordEncoder passwordEncoder, IUserMapper userMapper) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        Role roleUser = roleDao.getRoleByName(this.roleUser);
        User user = userMapper.toEntity(userDto);
        user.setRole(roleUser);
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDao.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userDao.getAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.toDto(userDao.getById(id));
    }

    @Override
    public void deleteUser(Long id) {
        userDao.delete(userDao.getById(id));
    }
}
