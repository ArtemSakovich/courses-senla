package com.company.api.dao;

import com.company.model.RoomAssignment;

import javax.persistence.EntityManager;
import java.util.List;

public interface IRoomAssignmentDao extends IGenericDao<RoomAssignment> {

    List<String> getThreeLastRoomAssigmentDates(EntityManager entityManager, Long roomId);

    List<RoomAssignment> getActiveRoomAssignmentsByGuestId(EntityManager entityManager, Long guestId);

    List<RoomAssignment> getActiveRoomAssignmentsByRoomId(EntityManager entityManager, Long roomId);
}
