package com.company.dao;

import com.company.api.dao.IRoomDao;
import com.company.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RoomDao extends AbstractDao<Room> implements IRoomDao {

    private EntityManager entityManager;

    @Autowired
    private RoomDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private final String PRICE_PARAM_TO_SORT = "roomPrice";
    private final String NUMBER_OF_BEDS_PARAM_TO_SORT = "numberOfBeds";
    private final String NUMBER_OF_STARS_PARAM_TO_SORT = "numberOfStars";

    @Override
    protected Class getEntityClass() {
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

}