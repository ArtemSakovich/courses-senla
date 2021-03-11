package com.company.dao;

import com.company.api.dao.IMaintenanceDao;
import com.company.api.dao.IRoomAssignmentDao;
import com.company.model.Maintenance;

public class MaintenanceDao extends AbstractDao<Maintenance> implements IMaintenanceDao {
    private static IMaintenanceDao instance;

    private MaintenanceDao() {
    }

    public static IMaintenanceDao getInstance() {
        if (instance == null) {
            instance = new MaintenanceDao();
        }
        return instance;
    }

    @Override
    public void update(Maintenance updatedMaintenance) {
        Maintenance maintenance = getById(updatedMaintenance.getId());
        maintenance.setMaintenanceName(updatedMaintenance.getMaintenanceName());
        maintenance.setMaintenancePrice(updatedMaintenance.getMaintenancePrice());
    }

}
