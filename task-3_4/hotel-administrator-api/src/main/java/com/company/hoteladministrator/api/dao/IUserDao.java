package com.company.hoteladministrator.api.dao;

import com.company.hoteladministrator.model.User;

public interface IUserDao extends IGenericDao<User> {

    User getUserByUsername(String username);
}
