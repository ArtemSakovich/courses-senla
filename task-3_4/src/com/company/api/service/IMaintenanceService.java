package com.company.api.service;

import com.company.model.Guest;
import com.company.model.Maintenance;
import com.company.model.MaintenanceSection;
import com.company.util.comparators.maintenanceComparators.MaintenancePriceComparator;
import com.company.util.comparators.maintenanceComparators.MaintenanceSectionComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface IMaintenanceService {
    Maintenance addMaintenance(String name, Double price, MaintenanceSection section);

    void update(Maintenance updatedMaintenance);

    Maintenance getById(Long id);

    void changeMaintenancePrice(Maintenance maintenance, Double newPrice);

    List<Maintenance> getAllMaintenances();

    public List<Maintenance> sortMaintenancesOfCertainGuestByPrice(Guest guest);

    public List<Maintenance> sortMaintenancesOfCertainGuestByOrderDate(Guest guest);

    public List<Maintenance> sortAllMaintenancesByPrice();

    public List<Maintenance> sortAllMaintenancesBySectionABC();
}
