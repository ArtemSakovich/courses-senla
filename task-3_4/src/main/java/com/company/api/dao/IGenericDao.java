package com.company.api.dao;

import com.company.model.AEntity;

import java.util.List;

public interface IGenericDao<T extends AEntity> {
    T getById(Long id);

    List<T> getAll();

    void save(T entity);

    void saveAll(List<T> entityList);

    void update(T entity);

    void delete(T entity);

    Long getMaxId();
}
