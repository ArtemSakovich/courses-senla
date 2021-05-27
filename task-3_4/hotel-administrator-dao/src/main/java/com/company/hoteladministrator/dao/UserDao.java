package com.company.hoteladministrator.dao;

import com.company.hoteladministrator.api.dao.IUserDao;
import com.company.hoteladministrator.dao.generic.AbstractDao;
import com.company.hoteladministrator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class UserDao extends AbstractDao<User> implements IUserDao {

    private final EntityManager entityManager;

    @Autowired
    private UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public User getUserByUsername(String username) {
        String SELECT_USER_BY_USERNAME = "from User where username =:username";
        return (User) entityManager.createQuery(SELECT_USER_BY_USERNAME).setParameter("username", username).getSingleResult();
    }
}
