package com.company.hoteladministrator.api.dao;

import com.company.hoteladministrator.model.generic.AEntity;

import java.util.List;

public interface IGenericDao<T extends AEntity> {

    T getById(Long id);

    List<T> getAll();

    void save(T entity);

    void update(T updatedEntity);

    void delete(T entity);

    List<T> getSortedEntities(String sortParam);
}
