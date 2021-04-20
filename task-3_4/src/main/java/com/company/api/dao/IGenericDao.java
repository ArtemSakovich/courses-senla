package com.company.api.dao;

import com.company.model.AEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IGenericDao<T extends AEntity> {

    T getById(Connection connection, Long id);

    List<T> getAll(Connection connection);

    void save(Connection connection, T entity);

    void update(Connection connection, T updatedEntity) throws SQLException;

    void delete(Connection connection, T entity);

    List<T> getSortedABCEntities(Connection connection);
}
