package com.company.handlers;

import com.company.model.Room;
import com.company.model.RoomAssignment;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomBeanListHandler extends BeanListHandler<Room> {
    private Connection connection;
    private final String QUERY = "SELECT * FROM roomassignment where roomId = ?";


    public RoomBeanListHandler(Connection con) {
        super(Room.class);
        this.connection = con;
    }

    @Override
    public List<Room> handle(ResultSet rs) throws SQLException {
        List<Room> rooms = super.handle(rs);

        QueryRunner runner = new QueryRunner();
        RoomAssignmentBeanListHandler roomAssignmentBeanListHandler = new RoomAssignmentBeanListHandler(connection);

        for (Room room : rooms) {
            List<RoomAssignment> roomAssignments
                    = runner.query(connection, QUERY, roomAssignmentBeanListHandler, room.getId());
            room.setRoomAssignments(roomAssignments);
        }
        return rooms;
    }
}