package com.company.api.service;

import com.company.model.Maintenance;

public interface IMaintenanceService {
    public Maintenance addMaintenance(String name, Double price);

    void update(Maintenance updatedMaintenance);

    Maintenance getById(Long id);

    void changeMaintenancePrice(Maintenance maintenance, Double newPrice);
}
