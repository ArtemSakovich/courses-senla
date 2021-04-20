package com.company.api.dao;

import com.company.model.Maintenance;

import java.sql.Connection;
import java.util.List;

public interface IMaintenanceDao extends IGenericDao<Maintenance> {

    List<Maintenance> getMaintenancesSortedByPrice(Connection connection);

    List<Maintenance> getAllOrderedMaintenances(Connection connection);

    List<Maintenance> getAllMaintenancesOfCertainGuest(Connection connection, Long guestId);

    List<Maintenance> sortMaintenancesByOrderDate(Connection connection, Long guestId);

    List<Maintenance> sortMaintenancesByPrice(Connection connection, Long guestId);
}
