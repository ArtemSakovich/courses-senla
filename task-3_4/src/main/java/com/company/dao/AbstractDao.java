package com.company.dao;

import com.company.api.dao.IGenericDao;
import com.company.api.exceptions.OperationCancelledException;
import com.company.model.AEntity;
import com.company.service.GuestService;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDao<T extends AEntity> implements IGenericDao<T> {
    private static final Logger log = Logger.getLogger(AbstractDao.class.getName());


    @Override
    public T getById(Connection connection, Long id) {
        final String SELECT_BY_ID = "SELECT * FROM " + getDBTableName() + " WHERE " + getDBIdColumnName() + " = ?";

            ResultSetHandler<T> resultHandler = getEntityBeanHandler(connection);
            QueryRunner queryRunner = new QueryRunner();

        try {
            return queryRunner.query(connection, SELECT_BY_ID, resultHandler, id);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Database exception when trying to get entity by id", e);
            throw new OperationCancelledException("Database exception when trying to get entity by id");
        }
    }

    @Override
    public List<T> getAll(Connection connection) {
        final String SELECT_ALL = "SELECT * FROM " + getDBTableName();
        return getAllInternal(connection, SELECT_ALL);
    }

    public List<T> getAllInternal(Connection connection, String SQL_QUERY) {
        BeanListHandler<T> resultSetHandler = getEntityBeanListHandler(connection);
        QueryRunner runner = new QueryRunner();
        try {
            return runner.query(connection, SQL_QUERY, resultSetHandler);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Database exception when trying to get all entities", e);
            throw new OperationCancelledException("Database exception when trying to get all entities");
        }
    }

    @Override
    public void save(Connection connection, T entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
            getPreparedStatementForInsertAndUpdate(preparedStatement, entity).executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Database exception when trying to save entity", e);
            throw new OperationCancelledException("Database exception when trying to save entity");
        }
    }

    @Override
    public void update(Connection connection, T updatedEntity) throws SQLException {
            PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery());
            getPreparedStatementForInsertAndUpdate(preparedStatement, updatedEntity);
    }

    @Override
    public void delete(Connection connection, T entity) {
        try {
            QueryRunner runner = new QueryRunner();
            String DELETE_QUERY = "DELETE FROM " + getDBTableName() + " WHERE " + getDBIdColumnName() + " = ?";
            runner.update(connection, DELETE_QUERY, entity.getId());
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Database exception when trying to delete entity", e);
            throw new OperationCancelledException("Database exception when trying to delete entity");
        }
    }

    @Override
    public List<T> getSortedABCEntities(Connection connection) {
        final String SELECT_ALL = "SELECT * FROM " + getDBTableName() + " ORDER BY " + getColumnNameForABCSort() + " ASC";
        return getAllInternal(connection, SELECT_ALL);
    }

    protected abstract String getDBTableName();

    protected abstract String getDBIdColumnName();

    protected abstract String getColumnNameForABCSort();

    protected abstract String getInsertQuery();

    protected abstract String getUpdateQuery();

    protected abstract PreparedStatement getPreparedStatementForInsertAndUpdate(PreparedStatement preparedStatement, T entity) throws SQLException;

    protected abstract BeanHandler<T> getEntityBeanHandler(Connection connection);

    protected abstract BeanListHandler<T> getEntityBeanListHandler(Connection connection);
}
