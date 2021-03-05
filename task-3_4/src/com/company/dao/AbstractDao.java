package com.company.dao;

import com.company.api.dao.IGenericDao;
import com.company.api.dao.IRoomDao;
import com.company.model.AEntity;

import java.util.ArrayList;
import java.util.List;

public class AbstractDao<T extends AEntity> implements IGenericDao<T> {
    private List<T> repository = new ArrayList<>();

    @Override
    public T getById(Long id) {
        for (T entity : repository) {
            if(id.equals(entity.getId())) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(repository);
    }

    @Override
    public void save(T entity) {
        repository.add(entity);
    }

    @Override
    public void update(T updatedEntity) {
    }

    @Override
    public void delete(T entity) {
        repository.remove(entity);
    }
}
