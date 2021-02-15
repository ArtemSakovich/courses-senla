package com.company.service;

import com.company.api.dao.IMaintenanceDao;
import com.company.api.service.IMaintenanceService;
import com.company.model.*;
import com.company.util.IdGenerator;
import com.company.util.comparators.maintenanceComparators.MaintenanceOrderDateComparator;
import com.company.util.comparators.maintenanceComparators.MaintenancePriceComparator;
import com.company.util.comparators.maintenanceComparators.MaintenanceSectionComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaintenanceService implements IMaintenanceService {

    private final IMaintenanceDao maintenanceDao;

    public MaintenanceService(IMaintenanceDao maintenanceDao) {
        this.maintenanceDao = maintenanceDao;
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
    public void changeMaintenancePrice(Maintenance maintenance, Double newPrice) {
        maintenance.setMaintenancePrice(newPrice);
    }

    @Override
    public List<Maintenance> getAllMaintenances() {
        return maintenanceDao.getAll();
    }

    @Override
    public List<Maintenance> sortAllMaintenancesByPrice() {
        List<Maintenance> maintenancesToSort = new ArrayList<>(getAllMaintenances());
        Collections.sort(maintenancesToSort, new MaintenancePriceComparator());
        return maintenancesToSort;
    }

    @Override
    public List<Maintenance> sortAllMaintenancesBySectionABC() {
        List<Maintenance> maintenancesToSort = new ArrayList<>(getAllMaintenances());
        Collections.sort(maintenancesToSort, new MaintenanceSectionComparator());
        return maintenancesToSort;
    }

    @Override
    public List<Maintenance> sortMaintenancesOfCertainGuestByPrice(Guest guest) {
        if (guest.getActiveRoomAssignments().isEmpty()) {
            System.out.println("Unfortunately, " + guest.getName() + " " +
                    guest.getSurname() + " currently currently not a hotel guest");
            return null;
        } else {
            List<Maintenance> maintenancesToSort = new ArrayList<>();
            for (RoomAssignment roomAssignment : guest.getActiveRoomAssignments()) {
                if (roomAssignment.getMaintenances().isEmpty()) {
                    System.out.println("Unfortunately, " + guest.getName() + " " +
                            guest.getSurname() + " did not order any maintenances");
                    return null;
                } else {
                    maintenancesToSort.addAll(roomAssignment.getMaintenances());
                    Collections.sort(maintenancesToSort, new MaintenancePriceComparator());
                    return maintenancesToSort;
                }
            }
        }
        return null;
    }

        @Override
        public List<Maintenance> sortMaintenancesOfCertainGuestByOrderDate (Guest guest){
            if (guest.getActiveRoomAssignments().isEmpty()) {
                System.out.println("Unfortunately, " + guest.getName() + " " +
                        guest.getSurname() + " currently currently not a hotel guest");
                return null;
            } else {
                List<Maintenance> maintenancesToSort = new ArrayList<>();
                for (RoomAssignment roomAssignment : guest.getActiveRoomAssignments()) {
                    if (roomAssignment.getMaintenances().isEmpty()) {
                        System.out.println("Unfortunately, " + guest.getName() + " " +
                                guest.getSurname() + " did not order any maintenances");
                        return null;
                    } else {
                        maintenancesToSort.addAll(roomAssignment.getMaintenances());
                        Collections.sort(maintenancesToSort, new MaintenanceOrderDateComparator());
                        return maintenancesToSort;
                    }
                }
            }
            return null;
        }
    }