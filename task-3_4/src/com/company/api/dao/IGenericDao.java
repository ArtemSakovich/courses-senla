package com.company.api.dao;

import com.company.model.AEntity;

import java.util.List;

public interface IGenericDao<T extends AEntity> {
    T getById(Long id);

    List<T> getAll();

    void save(T entity);

    void update(T entity);

    void delete(T entity);
}
