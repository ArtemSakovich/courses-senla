package com.company.api.dao;

import com.company.model.RoomAssignment;

import java.sql.Connection;
import java.util.List;

public interface IRoomAssignmentDao extends IGenericDao<RoomAssignment> {

    List<String> getThreeLastRoomAssigmentDates(Connection connection, Long roomId);

    List<RoomAssignment> getActiveRoomAssignmentsByGuestId(Connection connection, Long guestId);

    List<RoomAssignment> getActiveRoomAssignmentsByRoomId(Connection connection, Long roomId);
}
