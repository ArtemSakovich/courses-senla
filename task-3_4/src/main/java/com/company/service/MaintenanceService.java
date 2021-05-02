package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IMaintenanceDao;
import com.company.api.dao.IRoomAssignmentDao;
import com.company.api.service.IMaintenanceService;
import com.company.model.Maintenance;
import com.company.model.MaintenanceSection;
import com.company.model.OrderedMaintenance;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class MaintenanceService implements IMaintenanceService {
    @Autowired
    private IMaintenanceDao maintenanceDao;
    @Autowired
    private IGuestDao guestDao;
    @Autowired
    private IRoomAssignmentDao roomAssignmentDao;

    private static final Logger log = Logger.getLogger(MaintenanceService.class.getName());

    private MaintenanceService() {

    }

    @Override
    @Transactional
    public Maintenance addMaintenance(String name, Double price, MaintenanceSection section) {
        Maintenance maintenance = new Maintenance(name, price, section);
        maintenanceDao.save(maintenance);
        return maintenance;
    }

    @Override
    public void changeMaintenancePrice(Long id, Double newPrice) {
        Maintenance maintenanceToChangePrice = maintenanceDao.getById(id);
        if (maintenanceToChangePrice == null) {
            log.warn("Incorrect input when trying to change maintenance price");
            throw new IllegalArgumentException("Maintenance not found!");
        } else {
            maintenanceToChangePrice.setMaintenancePrice(newPrice);
            maintenanceDao.update(maintenanceToChangePrice);
        }
    }

    @Override
    public List<Maintenance> getSortedMaintenances(String paramToSort) {
        return maintenanceDao.getSortedMaintenances(paramToSort);
    }

    @Override
    public List<Maintenance> getSortedMaintenancesOfCertainGuest(Long guestId, String paramToSort) {
        return maintenanceDao.getSortedMaintenancesOfCertainGuest(guestId, paramToSort).stream()
                .map(OrderedMaintenance::getMaintenance).collect(Collectors.toList());
    }

    @Override
    public List<Maintenance> getAllMaintenancesOfCertainGuest(Long guestId) {
        return maintenanceDao.getAllMaintenancesOfCertainGuest(guestId).stream()
                .map(OrderedMaintenance::getMaintenance).collect(Collectors.toList());
    }

    @Override
    public List<Maintenance> getAllOrderedMaintenances() {
        return maintenanceDao.getAllOrderedMaintenances();
    }
}