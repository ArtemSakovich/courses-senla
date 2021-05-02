package com.company.api.dao;

import com.company.model.RoomAssignment;

import javax.persistence.EntityManager;
import java.util.List;

public interface IRoomAssignmentDao extends IGenericDao<RoomAssignment> {

    List<String> getThreeLastRoomAssigmentDates(Long roomId);

    List<RoomAssignment> getActiveRoomAssignmentsByGuestId(Long guestId);

    List<RoomAssignment> getActiveRoomAssignmentsByRoomId(Long roomId);
}
