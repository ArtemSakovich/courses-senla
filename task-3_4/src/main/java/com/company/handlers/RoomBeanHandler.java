package com.company.handlers;

import com.company.model.Room;
import com.company.model.RoomAssignment;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomBeanHandler extends BeanHandler<Room> {
    private Connection connection;
    private final String QUERY = "SELECT * FROM maintenance m INNER JOIN " +
            "orderedMaintenances om ON m.id = om.maintenanceid where roomassignmentid = ? ";


    public RoomBeanHandler(Connection con) {
        super(Room.class);
        this.connection = con;
    }

    @Override
    public Room handle(ResultSet rs) throws SQLException {
        Room room = super.handle(rs);

        QueryRunner runner = new QueryRunner();
        RoomAssignmentBeanListHandler roomAssignmentBeanListHandler = new RoomAssignmentBeanListHandler(connection);

        List<RoomAssignment> roomAssignments = runner.query(connection, QUERY, roomAssignmentBeanListHandler, room.getId());
        room.setRoomAssignments(roomAssignments);
        return room;
    }
}
