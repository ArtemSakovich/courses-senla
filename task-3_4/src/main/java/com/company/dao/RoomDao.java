package com.company.dao;

import com.company.api.dao.IRoomDao;
import com.company.injection.annotation.DependencyClass;
import com.company.model.Room;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

@DependencyClass
public class RoomDao extends AbstractDao<Room> implements IRoomDao {

    private final String PRICE_PARAM_TO_SORT = "roomPrice";
    private final String NUMBER_OF_BEDS_PARAM_TO_SORT = "numberOfBeds";
    private final String NUMBER_OF_STARS_PARAM_TO_SORT = "numberOfStars";

    @Override
    protected Class getEntityClass() {
        return Room.class;
    }

    @Override
    protected String getColumnNameForABCSort() {
        final String ROOM_COLUMN_NAME_FOR_ABC_SORT = "roomStatus";
        return ROOM_COLUMN_NAME_FOR_ABC_SORT;
    }

    @Override
    public List<Room> sortRoomsByCheckOutDate(EntityManager entityManager) {
        String SORT_ROOMS_BY_CHECKOUT_DATE = "from Room r left join RoomAssignment ra on r.id = ra.room.id order by ra.checkOutDate";
        return entityManager.createQuery(SORT_ROOMS_BY_CHECKOUT_DATE).getResultList();
    }

    @Override
    public List<Room> getFreeRooms(EntityManager entityManager) {
        String SELECT_FREE_ROOMS = "from Room where roomStatus = 'FREE'";
        return entityManager.createQuery(SELECT_FREE_ROOMS).getResultList();
    }

    @Override
    public List<Room> getFreeRoomsByDate(EntityManager entityManager, LocalDateTime requiredDate) {
        String SELECT_FREE_ROOMS_BY_DATE = "from Room r inner join RoomAssignment ra on r.id = ra.room.id where ra.checkOutDate < :checkOutDate";
        return entityManager.createQuery(SELECT_FREE_ROOMS_BY_DATE).setParameter("checkOutDate", requiredDate).getResultList();
    }

    public List<Room> getFreeSortedRooms(EntityManager entityManager, String paramToSort) {
        String SELECT_FREE_SORTED_ROOMS = "from Room r where r.roomStatus = 'FREE' order by :paramToSort";
        return entityManager.createQuery(SELECT_FREE_SORTED_ROOMS).setParameter("paramToSort", paramToSort).getResultList();
    }

    @Override
    public List<Room> getRoomsSortedByPrice(EntityManager entityManager) {
        return getSortedEntities(entityManager, PRICE_PARAM_TO_SORT);
    }

    @Override
    public List<Room> getRoomsSortedByNumberOfBeds(EntityManager entityManager) {
        return getSortedEntities(entityManager, NUMBER_OF_BEDS_PARAM_TO_SORT);
    }

    @Override
    public List<Room> getRoomsSortedByNumberOfStars(EntityManager entityManager) {
        return getSortedEntities(entityManager, NUMBER_OF_STARS_PARAM_TO_SORT);
    }

    @Override
    public List<Room> sortFreeRoomsByPrice(EntityManager entityManager) {
        return getFreeSortedRooms(entityManager, PRICE_PARAM_TO_SORT);
    }

    @Override
    public List<Room> sortFreeRoomsByNumberOfBeds(EntityManager entityManager) {
        return getFreeSortedRooms(entityManager, NUMBER_OF_BEDS_PARAM_TO_SORT);
    }

    @Override
    public List<Room> sortFreeRoomsByNumberOfStars(EntityManager entityManager) {
        return getFreeSortedRooms(entityManager, NUMBER_OF_STARS_PARAM_TO_SORT);
    }
}