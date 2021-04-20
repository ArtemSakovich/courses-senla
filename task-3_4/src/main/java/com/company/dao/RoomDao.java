package com.company.dao;

import com.company.api.dao.IRoomDao;
import com.company.api.exceptions.OperationCancelledException;
import com.company.handlers.RoomBeanHandler;
import com.company.handlers.RoomBeanListHandler;
import com.company.injection.annotation.DependencyClass;
import com.company.model.Room;
import com.company.service.GuestService;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@DependencyClass
public class RoomDao extends AbstractDao<Room> implements IRoomDao {
    private static final Logger log = Logger.getLogger(RoomDao.class.getName());


    private final String SELECT_ROOMS_SORTED_BY = "SELECT * FROM room ORDER BY ? ASC";
    private final String SELECT_FREE_ROOMS_SORTED_BY = "SELECT * FROM room WHERE roomStatus = 'FREE' ORDER BY ? ASC";

    @Override
    public void update(Connection connection, Room updatedRoom) {
        Room room = getById(connection, updatedRoom.getId());
        room.setRoomNumber(updatedRoom.getRoomNumber());
        room.setRoomPrice(updatedRoom.getRoomPrice());
        room.setRoomStatus(updatedRoom.getRoomStatus());
        room.setNumberOfBeds(updatedRoom.getNumberOfBeds());
    }

    @Override
    protected String getInsertQuery() {
        final String INSERT_QUERY = "INSERT INTO room (roomNumber, roomStatus, roomPrice, numberOfBeds, " +
                "numberOfStars) VALUES (?, ?, ?, ?, ?, ?)";
        return INSERT_QUERY;
    }

    @Override
    protected String getUpdateQuery() {
        final String UPDATE_QUERY = "UPDATE room SET roomNumber = ?, roomStatus = ?, roomPrice = ?," +
                " numberOfBeds = ?, numberOfStars = ? WHERE id = ?";
        return UPDATE_QUERY;
    }

    @Override
    protected PreparedStatement getPreparedStatementForInsertAndUpdate(PreparedStatement preparedStatement, Room room) throws SQLException {
        preparedStatement.setLong(1, room.getId());
        preparedStatement.setInt(2, room.getRoomNumber());
        preparedStatement.setString(3, room.getRoomStatus().toString());
        preparedStatement.setDouble(4, room.getRoomPrice());
        preparedStatement.setInt(5, room.getNumberOfBeds());
        preparedStatement.setInt(6, room.getNumberOfStars());
        return preparedStatement;
    }

    @Override
    protected BeanHandler<Room> getEntityBeanHandler(Connection connection) {
        return new RoomBeanHandler(connection);
    }

    @Override
    protected BeanListHandler<Room> getEntityBeanListHandler(Connection connection) {
        return new RoomBeanListHandler(connection);
    }

    @Override
    public String getDBTableName() {
        final String ROOM_TABLE_NAME = "room";
        return ROOM_TABLE_NAME;
    }

    @Override
    public String getDBIdColumnName() {
        final String ROOM_ID_COLUMN_NAME = "id";
        return ROOM_ID_COLUMN_NAME;
    }

    @Override
    protected String getColumnNameForABCSort() {
        final String ROOM_COLUMN_NAME_FOR_ABC_SORT = "roomStatus";
        return ROOM_COLUMN_NAME_FOR_ABC_SORT;
    }

    @Override
    public List<Room> sortRoomsByCheckOutDate(Connection connection) {
        final String SELECT_ROOMS_SORTED_BY_ORDER_DATE = "SELECT * FROM room r INNER JOIN roomassignment ra ON r.id = ra.roomid ORDER BY ra.checkoutdate";
        return getAllInternal(connection, SELECT_ROOMS_SORTED_BY_ORDER_DATE);
    }

    @Override
    public List<Room> getFreeRooms(Connection connection) {
        final String SELECT_FREE_ROOMS = "SELECT * FROM room WHERE roomStatus = 'FREE' ";
        return getAllInternal(connection, SELECT_FREE_ROOMS);
    }

    @Override
    public List<Room> getFreeRoomsByDate(Connection connection, LocalDateTime requiredDate) {
        final String SELECT_FREE_ROOMS_BY_DATE = "SELECT * FROM room r INNER JOIN roomassignment ra ON r.id = ra.roomid WHERE ra.checkOutDate < " + requiredDate;
        return getAllInternal(connection, SELECT_FREE_ROOMS_BY_DATE);
    }

    public List<Room> getSortedRooms(Connection connection, String SQL_QUERY, String paramToSort) {
            BeanListHandler<Room> resultSetHandler = getEntityBeanListHandler(connection);
            QueryRunner runner = new QueryRunner();
        try {
            return runner.query(connection, SQL_QUERY, resultSetHandler, paramToSort);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Database exception when trying to get sorted list of rooms", e);
            throw new OperationCancelledException("Database exception when trying sorted list of rooms");
        }
    }

    @Override
    public List<Room> getRoomsSortedByPrice(Connection connection) {
        return getSortedRooms(connection, SELECT_ROOMS_SORTED_BY, "roomPrice");
    }

    @Override
    public List<Room> getRoomsSortedByNumberOfBeds(Connection connection) {
        return getSortedRooms(connection, SELECT_ROOMS_SORTED_BY, "numberOfBeds");
    }

    @Override
    public List<Room> getRoomsSortedByNumberOfStars(Connection connection) {
        return getSortedRooms(connection, SELECT_ROOMS_SORTED_BY, "numberOfStars");
    }

    @Override
    public List<Room> sortFreeRoomsByPrice(Connection connection) {
        return getSortedRooms(connection, SELECT_FREE_ROOMS_SORTED_BY, "roomPrice");
    }

    @Override
    public List<Room> sortFreeRoomsByNumberOfBeds(Connection connection) {
        return getSortedRooms(connection, SELECT_FREE_ROOMS_SORTED_BY, "numberOfBeds");
    }

    @Override
    public List<Room> sortFreeRoomsByNumberOfStars(Connection connection) {
        return getSortedRooms(connection, SELECT_FREE_ROOMS_SORTED_BY, "numberOfStars");
    }
}