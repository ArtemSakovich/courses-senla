package com.company.api.service;

import com.company.model.Maintenance;
import com.company.model.MaintenanceSection;

import java.util.List;

public interface IMaintenanceService {
    Maintenance addMaintenance(String name, Double price, MaintenanceSection section);

    void changeMaintenancePrice(Long id, Double newPrice);

    List<Maintenance> getSortedMaintenances(String paramToSort);

    List<Maintenance> getAllMaintenancesOfCertainGuest(Long guestId);

    List<Maintenance> getSortedMaintenancesOfCertainGuest(Long guestId, String paramToSort);

    List<Maintenance> getAllOrderedMaintenances();
}
