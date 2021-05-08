package com.company.api.dao;

import com.company.model.Maintenance;
import com.company.model.OrderedMaintenance;

import javax.persistence.EntityManager;
import java.util.List;

public interface IMaintenanceDao extends IGenericDao<Maintenance> {

    List<Maintenance> getSortedMaintenances(String paramToSort);

    List<Maintenance> getAllOrderedMaintenances();

    List<OrderedMaintenance> getAllMaintenancesOfCertainGuest(Long guestId);

    List<OrderedMaintenance> getSortedMaintenancesOfCertainGuest(Long guestId, String paramToSort);
}
