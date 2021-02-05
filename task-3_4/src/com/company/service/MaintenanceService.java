package com.company.service;

import com.company.api.dao.IMaintenanceDao;
import com.company.api.service.IMaintenanceService;
import com.company.model.Maintenance;
import com.company.util.IdGenerator;

public class MaintenanceService implements IMaintenanceService {
    private final IMaintenanceDao maintenanceDao;

    public MaintenanceService(IMaintenanceDao maintenanceDao) {
        this.maintenanceDao = maintenanceDao;
    }
    @Override
    public Maintenance addMaintenance(String name, Double price) {
        Maintenance maintenance = new Maintenance(name, price);
        maintenance.setId(IdGenerator.generateMaintenanceId());
        maintenanceDao.save(maintenance);
        return maintenance;
    }

    @Override
    public void update(Maintenance updatedMaintenance) {
        maintenanceDao.update(updatedMaintenance);
    }

    @Override
    public Maintenance getById(Long id) {
        return maintenanceDao.getById(id);
    }

    @Override
    public void changeMaintenancePrice(Maintenance maintenance, Double newPrice) {
        maintenance.setMaintenancePrice(newPrice);
    }
}
