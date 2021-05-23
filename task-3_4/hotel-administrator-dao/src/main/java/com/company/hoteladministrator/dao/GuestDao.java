package com.company.hoteladministrator.dao;

import com.company.hoteladministrator.api.dao.IGuestDao;
import com.company.hoteladministrator.dao.generic.AbstractDao;
import com.company.hoteladministrator.model.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class GuestDao extends AbstractDao<Guest> implements IGuestDao {

    private EntityManager entityManager;

    @Autowired
    private GuestDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected Class getEntityClass() {
        return Guest.class;
    }

    @Override
    public List<Guest> getLastGuests(Long roomId, int allowedNumberOfNotes) {
        String SELECT_THREE_LAST_GUESTS_HQL_QUERY = "select g FROM Guest g INNER JOIN RoomAssignment ra ON g.id=ra.guest.id " +
                "WHERE ra.room.id =:roomId order by ra.createdOn desc";
        return entityManager.createQuery(SELECT_THREE_LAST_GUESTS_HQL_QUERY).setParameter("roomId", roomId).setMaxResults(allowedNumberOfNotes).getResultList();
    }
}
