package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IMaintenanceDao;
import com.company.api.exceptions.OperationCancelledException;
import com.company.api.service.IMaintenanceService;
import com.company.model.*;
import com.company.util.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MaintenanceService implements IMaintenanceService {

    private final IMaintenanceDao maintenanceDao;
    private final IGuestDao guestDao;
    Logger log = Logger.getLogger(Maintenance.class.getName());

    public MaintenanceService(IMaintenanceDao maintenanceDao, IGuestDao guestDao) {
        this.maintenanceDao = maintenanceDao;
        this.guestDao = guestDao;
    }

    @Override
    public Maintenance addMaintenance(String name, Double price, MaintenanceSection section) {
        Maintenance maintenance = new Maintenance(name, price, section);
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
    public void changeMaintenancePrice(Long id, Double newPrice) {
        Maintenance maintenanceToChangePrice = maintenanceDao.getById(id);
        if (maintenanceToChangePrice == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to change maintenance price");
            throw new IllegalArgumentException("Maintenance not found!");
        } else {
            maintenanceToChangePrice.setMaintenancePrice(newPrice);
        }
    }

    @Override
    public List<Maintenance> getAllMaintenances() {
        return maintenanceDao.getAll();
    }

    @Override
    public List<Maintenance> sortAllMaintenancesByPrice() {
        List<Maintenance> maintenancesToSort = new ArrayList<>(getAllMaintenances());
        maintenancesToSort.sort(Comparator.comparingDouble(Maintenance::getMaintenancePrice));
        return maintenancesToSort;
    }

    @Override
    public List<Maintenance> sortAllMaintenancesBySectionABC() {
        List<Maintenance> maintenancesToSort = new ArrayList<>(getAllMaintenances());
        maintenancesToSort.sort(Comparator.comparing(Maintenance::getMaintenanceSectionAsString));
        return maintenancesToSort;
    }

    @Override
    public List<Maintenance> sortMaintenancesOfCertainGuestByPrice(Long guestId) {
        Guest guestToViewSortedMaintenances = guestDao.getById(guestId);
        if (guestToViewSortedMaintenances == null) {
            log.log(Level.SEVERE, "Incorrect input when trying view sorted by price maintenances of certain guest");
            throw new IllegalArgumentException("Guest not found");
        } else {
            List<Maintenance> maintenancesToSort = new ArrayList<>();
            for (RoomAssignment roomAssignment : guestToViewSortedMaintenances.getActiveRoomAssignments()) {
                maintenancesToSort.addAll(roomAssignment.getMaintenances());
            }
            return maintenancesToSort.stream()
                    .sorted(Comparator.comparing(Maintenance::getMaintenancePrice))
                    .collect(Collectors.toList());
        }
    }
}