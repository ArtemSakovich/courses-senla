package com.company.dao;

import com.company.api.dao.IRoomAssignmentDao;
import com.company.api.exceptions.OperationCancelledException;
import com.company.handlers.RoomAssignmentBeanHandler;
import com.company.handlers.RoomAssignmentBeanListHandler;
import com.company.injection.annotation.DependencyClass;
import com.company.model.RoomAssignment;
import com.company.service.GuestService;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@DependencyClass
public class RoomAssignmentDao extends AbstractDao<RoomAssignment> implements IRoomAssignmentDao {
    private static final Logger log = Logger.getLogger(RoomAssignmentDao.class.getName());

    @Override
    public String getDBTableName() {
        final String ROOM_ASSIGNMENT_TABLE_NAME = "roomAssignment";
        return ROOM_ASSIGNMENT_TABLE_NAME;
    }

    @Override
    public String getDBIdColumnName() {
        final String ROOM_ASSIGNMENT_ID_COLUMN_NAME = "id";
        return ROOM_ASSIGNMENT_ID_COLUMN_NAME;
    }

    @Override
    protected String getColumnNameForABCSort() {
        final String ROOM_ASSIGNMENT_COLUMN_NAME_FOR_ABC_SORT = "roomAssignmentStatus";
        return ROOM_ASSIGNMENT_COLUMN_NAME_FOR_ABC_SORT;
    }

    @Override
    protected String getInsertQuery() {
        final String INSERT_QUERY = "INSERT INTO roomAssignment (guestId, roomId, checkInDate, checkOutDate, " +
                "roomAssignmentStatus, createdOn) VALUES (?, ?, ?, ?, ?, ?)";
        return INSERT_QUERY;
    }

    @Override
    protected String getUpdateQuery() {
        final String UPDATE_QUERY = "UPDATE roomAssignment SET guestId = ?, roomId = ?, checkInDate = ?," +
                " checkOutDate = ?, roomAssignmentStatus = ?, createdOn = ? WHERE id = ?";
        return UPDATE_QUERY;
    }

    @Override
    protected PreparedStatement getPreparedStatementForInsertAndUpdate(PreparedStatement preparedStatement, RoomAssignment roomAssignment) throws SQLException {
        preparedStatement.setLong(1, roomAssignment.getGuestId());
        preparedStatement.setLong(2, roomAssignment.getRoomId());
        preparedStatement.setTimestamp(3, roomAssignment.getCheckInDate());
        preparedStatement.setTimestamp(4, roomAssignment.getCheckOutDate());
        preparedStatement.setString(5, roomAssignment.getRoomAssignmentStatus().toString());
        preparedStatement.setTimestamp(6, roomAssignment.getCreatedOn());
        return preparedStatement;
    }

    @Override
    protected BeanHandler<RoomAssignment> getEntityBeanHandler(Connection connection) {
        return new RoomAssignmentBeanHandler(connection);
    }

    @Override
    protected BeanListHandler<RoomAssignment> getEntityBeanListHandler(Connection connection) {
        return new RoomAssignmentBeanListHandler(connection);
    }

    @Override
    public void update(Connection connection, RoomAssignment updatedRoomAssignment) {
        RoomAssignment roomAssignment = getById(connection, updatedRoomAssignment.getId());
        roomAssignment.setRoomId(updatedRoomAssignment.getRoomId());
        roomAssignment.setCheckInDate(updatedRoomAssignment.getCheckInDate());
        roomAssignment.setCheckOutDate(updatedRoomAssignment.getCheckOutDate());
        roomAssignment.setCreatedOn(updatedRoomAssignment.getCreatedOn());
        roomAssignment.setGuestId(updatedRoomAssignment.getGuestId());
        roomAssignment.setMaintenances(updatedRoomAssignment.getMaintenances());
        roomAssignment.setRoomAssignmentStatus(updatedRoomAssignment.getRoomAssignmentStatus());
    }

    @Override
    public List<String> getThreeLastRoomAssigmentDates(Connection connection, Long roomId) {
        final String SELECT_FREE_LAST_ROOM_ASSIGNMENTS_DATES = "SELECT checkindate, checkoutdate FROM roomAssignment WHERE roomId = ? ORDER BY createdOn DESC LIMIT 3";
        BeanListHandler<RoomAssignment> resultSetHandler = getEntityBeanListHandler(connection);
        QueryRunner runner = new QueryRunner();
        try {
            List<RoomAssignment> threeLastRoomAssignments = runner.query(connection, SELECT_FREE_LAST_ROOM_ASSIGNMENTS_DATES, resultSetHandler, roomId);
            List<String> threeLastGuestsCheckInDates = new ArrayList<>();
            for (RoomAssignment roomAssignment : threeLastRoomAssignments) {
                threeLastGuestsCheckInDates.add("From: " + roomAssignment.getCheckInDate().toLocalDateTime().toLocalDate()
                        + " To: " + roomAssignment.getCheckOutDate().toLocalDateTime().toLocalDate());
            }
            return threeLastGuestsCheckInDates;
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Database exception when trying to get three last room assignment dates", e);
            throw new OperationCancelledException("Database exception when trying to get three last room assignment dates");
        }
    }

    @Override
    public List<RoomAssignment> getActiveRoomAssignmentsByGuestId(Connection connection, Long guestId) {
        final String SELECT_ACTIVE_ROOM_ASSIGNMENTS_BY_GUEST_ID = "SELECT * FROM roomAssignment WHERE guestId = ? AND roomAssignmentStatus = 'ACTIVE' ";
        return getActiveRoomAssignments(connection, SELECT_ACTIVE_ROOM_ASSIGNMENTS_BY_GUEST_ID, guestId);
    }

    @Override
    public List<RoomAssignment> getActiveRoomAssignmentsByRoomId(Connection connection, Long roomId) {
        final String SELECT_ACTIVE_ROOM_ASSIGNMENTS_BY_ROOM_ID = "SELECT * FROM roomAssignment WHERE roomId = ? AND roomAssignmentStatus = 'ACTIVE' ";
        return getActiveRoomAssignments(connection, SELECT_ACTIVE_ROOM_ASSIGNMENTS_BY_ROOM_ID, roomId);
    }

    private List<RoomAssignment> getActiveRoomAssignments(Connection connection, String SQL_QUERY, Long entityId) {
        BeanListHandler<RoomAssignment> resultSetHandler = getEntityBeanListHandler(connection);
        QueryRunner runner = new QueryRunner();
        try {
            return runner.query(connection, SQL_QUERY, resultSetHandler, entityId);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Database exception when trying to get active room assignments", e);
            throw new OperationCancelledException("Database exception when trying to get active room assignments");
        }
    }
}
