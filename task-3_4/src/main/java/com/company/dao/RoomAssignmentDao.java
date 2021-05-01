package com.company.dao;

import com.company.api.dao.IRoomAssignmentDao;
import com.company.model.RoomAssignment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RoomAssignmentDao extends AbstractDao<RoomAssignment> implements IRoomAssignmentDao {

    @Override
    protected Class getEntityClass() {
        return RoomAssignment.class;
    }

    @Override
    public List<String> getThreeLastRoomAssigmentDates(EntityManager entityManager, Long roomId) {
        String SELECT_THREE_LAST_ROOM_ASSIGNMENT_DATES = "select ra.checkInDate, ra.checkOutDate from RoomAssignment ra where ra.room.id = :roomId order by ra.createdOn desc";
        return entityManager.createQuery(SELECT_THREE_LAST_ROOM_ASSIGNMENT_DATES).setParameter("roomId", roomId).setMaxResults(3).getResultList();
    }

    @Override
    public List<RoomAssignment> getActiveRoomAssignmentsByGuestId(EntityManager entityManager, Long guestId) {
        String SELECT_ACTIVE_ROOM_ASSIGNMENTS_BY_GUEST_ID = "from RoomAssignment ra where ra.guest.id = :guestId and ra.roomAssignmentStatus = 'ACTIVE'";
        return entityManager.createQuery(SELECT_ACTIVE_ROOM_ASSIGNMENTS_BY_GUEST_ID).setParameter("guestId", guestId).getResultList();
    }

    @Override
    public List<RoomAssignment> getActiveRoomAssignmentsByRoomId(EntityManager entityManager, Long roomId) {
        String SELECT_ACTIVE_ROOM_ASSIGNMENTS_BY_ROOM_ID = "from RoomAssignment ra where ra.room.id = :roomId and ra.roomAssignmentStatus = 'ACTIVE'";
        return entityManager.createQuery(SELECT_ACTIVE_ROOM_ASSIGNMENTS_BY_ROOM_ID).setParameter("roomId", roomId).getResultList();
    }
}
