package com.company.api.service;

import com.company.model.Maintenance;
import com.company.model.MaintenanceSection;

import java.util.List;

public interface IMaintenanceService {
    Maintenance addMaintenance(String name, Double price, MaintenanceSection section);

    void update(Maintenance updatedMaintenance);

    Maintenance getById(Long id);

    void changeMaintenancePrice(Long id, Double newPrice);

    List<Maintenance> getAllMaintenances();

    public List<Maintenance> sortMaintenancesOfCertainGuestByPrice(Long guestId);

    public List<Maintenance> sortAllMaintenancesByPrice();

    public List<Maintenance> sortAllMaintenancesBySectionABC();
}
