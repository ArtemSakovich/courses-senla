package com.company.api.dao;

import com.company.model.Maintenance;
import com.company.model.OrderedMaintenance;

import javax.persistence.EntityManager;
import java.util.List;

public interface IMaintenanceDao extends IGenericDao<Maintenance> {

    List<Maintenance> getSortedMaintenances(EntityManager entityManager, String paramToSort);

    List<Maintenance> getAllOrderedMaintenances(EntityManager entityManager);

    List<OrderedMaintenance> getAllMaintenancesOfCertainGuest(EntityManager entityManager, Long guestId);

    List<OrderedMaintenance> getSortedMaintenancesOfCertainGuest(EntityManager entityManager, Long guestId, String paramToSort);
}
