package com.company.api.dao;

import com.company.model.AEntity;

import javax.persistence.EntityManager;
import java.util.List;

public interface IGenericDao<T extends AEntity> {

    T getById(EntityManager entityManager, Long id);

    List<T> getAll(EntityManager entityManager);

    void save(EntityManager entityManager, T entity);

    void update(EntityManager entityManager, T updatedEntity);

    void delete(EntityManager entityManager, T entity);

    List<T> getSortedEntities(EntityManager entityManager, String sortParam);
}
