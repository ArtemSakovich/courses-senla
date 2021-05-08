package com.company.dao;

import com.company.api.dao.IGuestDao;
import com.company.model.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class GuestDao extends AbstractDao<Guest> implements IGuestDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    protected Class getEntityClass() {
        return Guest.class;
    }

    @Override
    public List<Guest> getThreeLastGuests(Long roomId) {
        String SELECT_THREE_LAST_GUESTS_HQL_QUERY = "FROM Guest g INNER JOIN RoomAssignment ra ON g.id=ra.guest.id " +
                "WHERE ra.room.id =:roomId order by ra.createdOn desc";
        return entityManager.createQuery(SELECT_THREE_LAST_GUESTS_HQL_QUERY).setParameter("roomId", roomId).setMaxResults(3).getResultList();
    }
}
