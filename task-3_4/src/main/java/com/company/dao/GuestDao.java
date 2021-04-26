package com.company.dao;

import com.company.api.dao.IGuestDao;
import com.company.api.exceptions.OperationCancelledException;
import com.company.handlers.GuestBeanHandler;
import com.company.handlers.GuestBeanListHandler;
import com.company.injection.annotation.DependencyClass;
import com.company.model.Guest;
import com.company.service.GuestService;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@DependencyClass
public class GuestDao extends AbstractDao<Guest> implements IGuestDao {
    private static final Logger log = Logger.getLogger(GuestDao.class.getName());


    @Override
    public void update(Connection connection, Guest updatedGuest) {
        Guest guest = getById(connection, updatedGuest.getId());
        guest.setName(updatedGuest.getName());
        guest.setSurname(updatedGuest.getSurname());
        guest.setAge(updatedGuest.getAge());
    }

    @Override
    protected String getInsertQuery() {
        final String INSERT_QUERY = "INSERT INTO guest (name, surname, age) VALUES (?, ?, ?)";
        return INSERT_QUERY;
    }

    @Override
    protected String getUpdateQuery() {
        final String UPDATE_QUERY = "UPDATE guest SET name = ?, surname = ?, age = ? WHERE id = ?";
        return UPDATE_QUERY;
    }

    @Override
    public String getDBTableName() {
        final String GUEST_TABLE_NAME = "guest";
        return GUEST_TABLE_NAME;
    }

    @Override
    public String getDBIdColumnName() {
        final String GUEST_ID_COLUMN_NAME = "id";
        return GUEST_ID_COLUMN_NAME;
    }

    @Override
    protected String getColumnNameForABCSort() {
        final String GUEST_COLUMN_NAME_FOR_ABC_SORT = "name, surname";
        return GUEST_COLUMN_NAME_FOR_ABC_SORT;
    }

    @Override
    protected PreparedStatement getPreparedStatementForInsertAndUpdate(PreparedStatement preparedStatement, Guest guest) throws SQLException {
        preparedStatement.setString(1, guest.getName());
        preparedStatement.setString(2, guest.getSurname());
        preparedStatement.setInt(3, guest.getAge());
        return preparedStatement;
    }

    @Override
    protected BeanHandler<Guest> getEntityBeanHandler(Connection connection) {
        return new GuestBeanHandler(connection);
    }

    @Override
    protected BeanListHandler<Guest> getEntityBeanListHandler(Connection connection) {
        return new GuestBeanListHandler(connection);
    }

    @Override
    public List<Guest> getThreeLastGuests(Connection connection, Long roomId) {
        final String SELECT_THREE_LAST_GUESTS = "SELECT * FROM guest g INNER JOIN roomassignment ra ON " +
                "g.id = ra.guestid WHERE ra.roomId = ? ORDER BY ra.createdOn DESC LIMIT 3";
        BeanListHandler<Guest> resultSetHandler = getEntityBeanListHandler(connection);
            QueryRunner runner = new QueryRunner();
        try {
            return runner.query(connection, SELECT_THREE_LAST_GUESTS, resultSetHandler, roomId);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Database exception when trying to get three last guests by roomId", e);
            throw new OperationCancelledException("Database exception when trying to get three last guests by roomId");
        }
    }
}
