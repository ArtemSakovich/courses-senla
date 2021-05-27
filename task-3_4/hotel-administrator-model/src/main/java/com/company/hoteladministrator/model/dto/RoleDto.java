package com.company.hoteladministrator.model.dto;

import com.company.hoteladministrator.model.dto.generic.AbstractDto;

public class RoleDto extends AbstractDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
