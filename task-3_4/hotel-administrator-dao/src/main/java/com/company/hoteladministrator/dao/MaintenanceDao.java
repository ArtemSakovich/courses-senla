package com.company.hoteladministrator.dao;

import com.company.hoteladministrator.api.dao.IMaintenanceDao;
import com.company.hoteladministrator.dao.generic.AbstractDao;
import com.company.hoteladministrator.model.Maintenance;
import com.company.hoteladministrator.model.OrderedMaintenance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MaintenanceDao extends AbstractDao<Maintenance> implements IMaintenanceDao {

    private final EntityManager entityManager;

    @Autowired
    private MaintenanceDao (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected Class<Maintenance> getEntityClass() {
        return Maintenance.class;
    }

    @Override
    public List<Maintenance> getSortedMaintenances(String paramToSort) {
        return getSortedEntities(paramToSort);
    }

    @Override
    public List<Maintenance> getAllOrderedMaintenances() {
        String SELECT_ALL_ORDERED_MAINTENANCES = "select om.maintenance from OrderedMaintenance om";
        return entityManager.createQuery(SELECT_ALL_ORDERED_MAINTENANCES).getResultList();
    }

    @Override
    public List<OrderedMaintenance> getAllMaintenancesOfCertainGuest(Long guestId) {
        String SELECT_ALL_MAINTENANCES_OF_CERTAIN_GUEST = "select ra.maintenances FROM RoomAssignment ra where ra.guest.id = :guestId";
        return entityManager.createQuery(SELECT_ALL_MAINTENANCES_OF_CERTAIN_GUEST).setParameter("guestId", guestId).getResultList();
    }

    @Override
    public List<OrderedMaintenance> getSortedMaintenancesOfCertainGuest(Long guestId, String paramToSort) {
        String SELECT_ALL_MAINTENANCES_OF_CERTAIN_GUEST = "select ra.maintenances FROM" +
                " RoomAssignment ra inner join OrderedMaintenance om on ra.id = om.roomAssignment.id where ra.guest.id = :guestId order by :paramToSort";
        return entityManager.createQuery(SELECT_ALL_MAINTENANCES_OF_CERTAIN_GUEST).setParameter("guestId", guestId).setParameter("paramToSort", paramToSort).getResultList();
    }
}