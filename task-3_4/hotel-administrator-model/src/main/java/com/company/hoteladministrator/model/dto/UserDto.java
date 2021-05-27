package com.company.hoteladministrator.model.dto;

import com.company.hoteladministrator.model.dto.generic.AbstractDto;

public class UserDto extends AbstractDto {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
