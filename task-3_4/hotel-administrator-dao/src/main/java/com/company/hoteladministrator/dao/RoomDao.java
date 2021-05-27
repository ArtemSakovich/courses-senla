package com.company.hoteladministrator.dao;

import com.company.hoteladministrator.api.dao.IRoomDao;
import com.company.hoteladministrator.dao.generic.AbstractDao;
import com.company.hoteladministrator.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RoomDao extends AbstractDao<Room> implements IRoomDao {

    private final EntityManager entityManager;

    @Autowired
    private RoomDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected Class<Room> getEntityClass() {
        return Room.class;
    }

    @Override
    public List<Room> getFreeRooms() {
        String SELECT_FREE_ROOMS = "from Room where roomStatus = 'FREE'";
        return entityManager.createQuery(SELECT_FREE_ROOMS).getResultList();
    }

    @Override
    public List<Room> getFreeRoomsByDate(LocalDateTime requiredDate) {
        String SELECT_FREE_ROOMS_BY_DATE = "from Room r inner join RoomAssignment ra on r.id = ra.room.id where ra.checkOutDate < :checkOutDate";
        return entityManager.createQuery(SELECT_FREE_ROOMS_BY_DATE).setParameter("checkOutDate", requiredDate).getResultList();
    }

    @Override
    public List<Room> getFreeSortedRooms(String paramToSort) {
        String SELECT_FREE_SORTED_ROOMS = "from Room r where r.roomStatus = 'FREE' order by :paramToSort";
        return entityManager.createQuery(SELECT_FREE_SORTED_ROOMS).setParameter("paramToSort", paramToSort).getResultList();
    }

    @Override
    public Room getRoomByGuestId(Long guestId) {
        String SELECT_ROOM_BY_GUEST_ID = "from Room r inner join RoomAssignment ra on r.id = ra.room.id where ra.guest.id = :guestId";
        return (Room) entityManager.createQuery(SELECT_ROOM_BY_GUEST_ID).setParameter("guestId", guestId).getSingleResult();
    }

}