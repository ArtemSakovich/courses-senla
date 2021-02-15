package com.company.service;

import com.company.api.dao.IRoomAssignmentDao;
import com.company.api.service.IRoomAssignmentService;
import com.company.model.*;
import com.company.util.comparators.maintenanceComparators.MaintenancePriceComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomAssignmentService implements IRoomAssignmentService {

    private final IRoomAssignmentDao roomAssignmentDao;

    public RoomAssignmentService(IRoomAssignmentDao roomAssignmentDao) {
        this.roomAssignmentDao = roomAssignmentDao;
    }

    @Override
    public List<RoomAssignment> getAllAssignments() {
        return roomAssignmentDao.getAll();
    }

    @Override
    public List<Maintenance> getAllOrderedMaintenances() {
        List<Maintenance> orderedMaintenances = new ArrayList<>();
        for (RoomAssignment roomAssignment : getAllAssignments()) {
            orderedMaintenances.addAll(roomAssignment.getMaintenances());
        }
        return orderedMaintenances;
    }

    @Override
    public List<Maintenance> getAllMaintenancesOfCertainGuest(Guest guest) {
        List<Maintenance> allMaintenancesOfCertainGuest = new ArrayList<>();
        for (RoomAssignment roomAssignment : guest.getActiveRoomAssignments()) {
            allMaintenancesOfCertainGuest.addAll(roomAssignment.getMaintenances());
        }
        return allMaintenancesOfCertainGuest;
    }

}