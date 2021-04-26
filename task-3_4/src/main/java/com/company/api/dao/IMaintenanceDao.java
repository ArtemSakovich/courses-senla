package com.company.api.dao;

import com.company.model.Maintenance;
import com.company.model.OrderedMaintenance;

import javax.persistence.EntityManager;
import java.util.List;

public interface IMaintenanceDao extends IGenericDao<Maintenance> {

    List<Maintenance> getMaintenancesSortedByPrice(EntityManager entityManager);

    List<Maintenance> getAllOrderedMaintenances(EntityManager entityManager);

    List<OrderedMaintenance> getAllMaintenancesOfCertainGuest(EntityManager entityManager, Long guestId);

    List<OrderedMaintenance> sortMaintenancesByOrderDate(EntityManager entityManager, Long guestId);

    List<OrderedMaintenance> sortMaintenancesOfCertainGuestByPrice(EntityManager entityManager, Long guestId);
}
