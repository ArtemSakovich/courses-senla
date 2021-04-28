package com.company.dao;

import com.company.api.dao.IMaintenanceDao;
import com.company.injection.annotation.DependencyClass;
import com.company.model.Maintenance;
import com.company.model.OrderedMaintenance;

import javax.persistence.EntityManager;
import java.util.List;
@DependencyClass
public class MaintenanceDao extends AbstractDao<Maintenance> implements IMaintenanceDao {

    @Override
    protected Class getEntityClass() {
        return Maintenance.class;
    }

    @Override
    protected String getColumnNameForABCSort() {
        final String MAINTENANCE_COLUMN_NAME_FOR_ABC_SORT = "maintenanceSection";
        return MAINTENANCE_COLUMN_NAME_FOR_ABC_SORT;
    }

    @Override
    public List<Maintenance> getMaintenancesSortedByPrice(EntityManager entityManager) {
        String SORT_BY_PRICE_PARAM = "maintenancePrice";
        return getSortedEntities(entityManager, SORT_BY_PRICE_PARAM);
    }

    @Override
    public List<Maintenance> getAllOrderedMaintenances(EntityManager entityManager) {
        String SELECT_ALL_ORDERED_MAINTENANCES = "select om.maintenance from OrderedMaintenance om";
        return entityManager.createQuery(SELECT_ALL_ORDERED_MAINTENANCES).getResultList();
    }

    @Override
    public List<OrderedMaintenance> getAllMaintenancesOfCertainGuest(EntityManager entityManager, Long guestId) {
        String SELECT_ALL_MAINTENANCES_OF_CERTAIN_GUEST = "select ra.maintenances FROM RoomAssignment ra where ra.guest.id = :guestId";
        return entityManager.createQuery(SELECT_ALL_MAINTENANCES_OF_CERTAIN_GUEST).setParameter("guestId", guestId).getResultList();
    }

    @Override
    public List<OrderedMaintenance> sortMaintenancesByOrderDate(EntityManager entityManager, Long guestId) {
        String SELECT_ALL_MAINTENANCES_OF_CERTAIN_GUEST_SORTED_BY_ORDER_DATE = "select ra.maintenances FROM" +
                " RoomAssignment ra inner join OrderedMaintenance om on ra.id = om.roomAssignment.id where ra.guest.id = :guestId order by om.orderDate";
        return entityManager.createQuery(SELECT_ALL_MAINTENANCES_OF_CERTAIN_GUEST_SORTED_BY_ORDER_DATE).setParameter("guestId", guestId).getResultList();
    }

    @Override
    public List<OrderedMaintenance> sortMaintenancesOfCertainGuestByPrice(EntityManager entityManager, Long guestId) {
        String SELECT_ALL_MAINTENANCES_OF_CERTAIN_GUEST_SORTED_BY_PRICE = "select ra.maintenances FROM" +
                " RoomAssignment ra inner join OrderedMaintenance om on om.roomAssignment.id = ra.id where ra.guest.id = :guestId order by om.maintenance.maintenancePrice";
        return entityManager.createQuery(SELECT_ALL_MAINTENANCES_OF_CERTAIN_GUEST_SORTED_BY_PRICE).setParameter("guestId", guestId).getResultList();
    }
}