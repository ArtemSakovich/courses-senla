package com.company.dao;

import com.company.api.dao.IGuestDao;
import com.company.injection.annotation.DependencyClass;
import com.company.model.Guest;
import javax.persistence.EntityManager;
import java.util.List;

@DependencyClass
public class GuestDao extends AbstractDao<Guest> implements IGuestDao {

    @Override
    protected Class getEntityClass() {
        return Guest.class;
    }

    @Override
    protected String getColumnNameForABCSort() {
        final String GUEST_COLUMN_NAME_FOR_ABC_SORT = "name";
        return GUEST_COLUMN_NAME_FOR_ABC_SORT;
    }

    @Override
    public List<Guest> getThreeLastGuests(EntityManager entityManager, Long roomId) {
        String SELECT_THREE_LAST_GUESTS_HQL_QUERY = "FROM Guest g INNER JOIN RoomAssignment ra ON g.id=ra.guest.id " +
                "WHERE ra.room.id =:roomId order by ra.createdOn desc";
        return entityManager.createQuery(SELECT_THREE_LAST_GUESTS_HQL_QUERY).setParameter("roomId", roomId).setMaxResults(3).getResultList();
    }
}
