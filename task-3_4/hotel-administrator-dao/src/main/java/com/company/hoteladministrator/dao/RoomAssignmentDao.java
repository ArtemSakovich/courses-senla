package com.company.hoteladministrator.dao;

import com.company.hoteladministrator.api.dao.IRoomAssignmentDao;
import com.company.hoteladministrator.dao.generic.AbstractDao;
import com.company.hoteladministrator.model.RoomAssignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RoomAssignmentDao extends AbstractDao<RoomAssignment> implements IRoomAssignmentDao {

    private EntityManager entityManager;

    @Autowired
    private RoomAssignmentDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected Class getEntityClass() {
        return RoomAssignment.class;
    }

    @Override
    public List<String> getThreeLastRoomAssigmentDates(Long roomId) {
        String SELECT_THREE_LAST_ROOM_ASSIGNMENT_DATES = "select ra.checkInDate, ra.checkOutDate from RoomAssignment ra where ra.room.id = :roomId order by ra.createdOn desc";
        return entityManager.createQuery(SELECT_THREE_LAST_ROOM_ASSIGNMENT_DATES).setParameter("roomId", roomId).setMaxResults(3).getResultList();
    }

    @Override
    public List<RoomAssignment> getActiveRoomAssignmentsByGuestId(Long guestId) {
        String SELECT_ACTIVE_ROOM_ASSIGNMENTS_BY_GUEST_ID = "from RoomAssignment ra where ra.guest.id = :guestId and ra.roomAssignmentStatus = 'ACTIVE'";
        return entityManager.createQuery(SELECT_ACTIVE_ROOM_ASSIGNMENTS_BY_GUEST_ID).setParameter("guestId", guestId).getResultList();
    }

    @Override
    public List<RoomAssignment> getActiveRoomAssignmentsByRoomId(Long roomId) {
        String SELECT_ACTIVE_ROOM_ASSIGNMENTS_BY_ROOM_ID = "from RoomAssignment ra where ra.room.id = :roomId and ra.roomAssignmentStatus = 'ACTIVE'";
        return entityManager.createQuery(SELECT_ACTIVE_ROOM_ASSIGNMENTS_BY_ROOM_ID).setParameter("roomId", roomId).getResultList();
    }
}
