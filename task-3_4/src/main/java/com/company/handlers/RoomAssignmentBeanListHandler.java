package com.company.handlers;

import com.company.model.Maintenance;
import com.company.model.RoomAssignment;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomAssignmentBeanListHandler extends BeanListHandler<RoomAssignment> {
    private Connection connection;
    private final String QUERY = "SELECT * FROM maintenance m INNER JOIN " +
            "orderedMaintenances om ON m.id = om.maintenanceid where roomassignmentid = ? ";


    public RoomAssignmentBeanListHandler(Connection con) {
        super(RoomAssignment.class);
        this.connection = con;
    }

    @Override
    public List<RoomAssignment> handle(ResultSet rs) throws SQLException {
        List<RoomAssignment> roomAssignments = super.handle(rs);

        QueryRunner runner = new QueryRunner();
        BeanListHandler<Maintenance> handler = new BeanListHandler<>(Maintenance.class);

        for (RoomAssignment roomAssignment : roomAssignments) {
            List<Maintenance> maintenances
                    = runner.query(connection, QUERY, handler, roomAssignment.getId());
            roomAssignment.setMaintenances(maintenances);
        }
        return roomAssignments;
    }
}
