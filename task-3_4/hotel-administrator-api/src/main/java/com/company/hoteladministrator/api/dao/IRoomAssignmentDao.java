package com.company.hoteladministrator.api.dao;

import com.company.hoteladministrator.model.RoomAssignment;

import java.util.List;

public interface IRoomAssignmentDao extends IGenericDao<RoomAssignment> {

    List<String> getThreeLastRoomAssigmentDates(Long roomId);

    List<RoomAssignment> getActiveRoomAssignmentsByGuestId(Long guestId);

    List<RoomAssignment> getActiveRoomAssignmentsByRoomId(Long roomId);
}
