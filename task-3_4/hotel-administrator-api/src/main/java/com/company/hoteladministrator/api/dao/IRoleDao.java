package com.company.hoteladministrator.api.dao;

import com.company.hoteladministrator.model.Role;

public interface IRoleDao extends IGenericDao<Role> {

    Role getRoleByName(String roleName);
}
