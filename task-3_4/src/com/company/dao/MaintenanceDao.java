package com.company.dao;

import com.company.api.dao.IGenericDao;
import com.company.api.dao.IMaintenanceDao;
import com.company.model.Maintenance;

import java.util.*;

public class MaintenanceDao extends AbstractDao<Maintenance> implements IMaintenanceDao {
    @Override
    public void update(Maintenance updatedMaintenance) {
        Maintenance maintenance = getById(updatedMaintenance.getId());
        maintenance.setMaintenanceName(updatedMaintenance.getMaintenanceName());
        maintenance.setMaintenancePrice(updatedMaintenance.getMaintenancePrice());
    }

}
