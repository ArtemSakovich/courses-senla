package com.company.hoteladministrator.dao;

import com.company.hoteladministrator.api.dao.IRoleDao;
import com.company.hoteladministrator.dao.generic.AbstractDao;
import com.company.hoteladministrator.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class RoleDao extends AbstractDao<Role> implements IRoleDao {

    private final EntityManager entityManager;

    @Autowired
    private RoleDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }

    @Override
    public Role getRoleByName(String roleName) {
        String SELECT_ROLE_BY_NAME = "from Role where name =:roleName";
        return (Role) entityManager.createQuery(SELECT_ROLE_BY_NAME).setParameter("roleName", roleName);
    }
}
