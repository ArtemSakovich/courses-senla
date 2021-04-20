package com.company.handlers;

import com.company.model.Guest;
import com.company.model.RoomAssignment;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GuestBeanListHandler extends BeanListHandler<Guest> {
    private Connection connection;
    private final String QUERY = "SELECT * FROM roomassignment where guestId = ?";

    public GuestBeanListHandler(Connection con) {
        super(Guest.class);
        this.connection = con;
    }

    @Override
    public List<Guest> handle(ResultSet rs) throws SQLException {
        List<Guest> guests = super.handle(rs);

        QueryRunner runner = new QueryRunner();
        RoomAssignmentBeanListHandler roomAssignmentBeanListHandler = new RoomAssignmentBeanListHandler(connection);

        for (Guest guest : guests) {
            List<RoomAssignment> roomAssignments
                    = runner.query(connection, QUERY, roomAssignmentBeanListHandler, guest.getId());
            guest.setRoomAssignments(roomAssignments);
        }
        return guests;
    }
}
