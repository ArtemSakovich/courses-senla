package com.company.handlers;

import com.company.model.Guest;
import com.company.model.RoomAssignment;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GuestBeanHandler extends BeanHandler<Guest> {
    private Connection connection;
    private final String QUERY = "SELECT * FROM roomassignment where guestId = ?";

    public GuestBeanHandler(Connection con) {
        super(Guest.class);
        this.connection = con;
    }

    @Override
    public Guest handle(ResultSet rs) throws SQLException {
        Guest guest = super.handle(rs);

        QueryRunner runner = new QueryRunner();
        RoomAssignmentBeanListHandler roomAssignmentBeanListHandler = new RoomAssignmentBeanListHandler(connection);

        List<RoomAssignment> roomAssignments = runner.query(connection, QUERY, roomAssignmentBeanListHandler, guest.getId());
        guest.setRoomAssignments(roomAssignments);
        return guest;
    }
}