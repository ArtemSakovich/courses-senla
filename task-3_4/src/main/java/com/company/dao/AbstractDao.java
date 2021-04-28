package com.company.dao;

import com.company.api.dao.IGenericDao;
import com.company.model.AEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AbstractDao<T extends AEntity> implements IGenericDao<T> {

    @Override
    public T getById(EntityManager entityManager, Long id) {
        return entityManager.find(getEntityClass(), id);
    }

    @Override
    public List<T> getAll(EntityManager entityManager) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
            Root<T> rootEntry = cq.from(getEntityClass());
            CriteriaQuery<T> all = cq.select(rootEntry);
            TypedQuery<T> allQuery = entityManager.createQuery(all);
            return allQuery.getResultList();
    }

    @Override
    public void save(EntityManager entityManager, T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(EntityManager entityManager, T updatedEntity) {
        entityManager.merge(updatedEntity);
    }

    @Override
    public void delete(EntityManager entityManager, T entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<T> getSortedABCEntities(EntityManager entityManager) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
        Root<T> rootEntry = cq.from(getEntityClass());
        CriteriaQuery<T> all = cq.select(rootEntry).orderBy(cb.asc(rootEntry.get(getColumnNameForABCSort())));
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public List<T> getSortedEntities(EntityManager entityManager, String sortParam) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
        Root<T> rootEntry = cq.from(getEntityClass());
        CriteriaQuery<T> all = cq.select(rootEntry).orderBy(cb.asc(rootEntry.get(sortParam)));
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    protected abstract Class<T> getEntityClass();

    protected abstract String getColumnNameForABCSort();
}
