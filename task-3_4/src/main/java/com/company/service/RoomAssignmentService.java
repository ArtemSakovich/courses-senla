package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IRoomAssignmentDao;
import com.company.api.service.IRoomAssignmentService;
import com.company.configuration.annotation.ConfigClass;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;
import com.company.model.Guest;
import com.company.model.Maintenance;
import com.company.model.Room;
import com.company.model.RoomAssignment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
@ConfigClass
@DependencyClass
public class RoomAssignmentService implements IRoomAssignmentService {
    @DependencyComponent
    private IRoomAssignmentDao roomAssignmentDao;
    @DependencyComponent
    private IGuestDao guestDao;
    private static final Logger log = Logger.getLogger(RoomAssignmentService.class.getName());

    private RoomAssignmentService() {
    }

    @Override
    public void update(RoomAssignment updatedRoomAssignment) {
        roomAssignmentDao.update(updatedRoomAssignment);
    }

    @Override
    public RoomAssignment getById(Long id) {
        return roomAssignmentDao.getById(id);
    }

    @Override
    public List<RoomAssignment> getAllAssignments() {
        return roomAssignmentDao.getAll();
    }

    @Override
    public List<List<Maintenance>> getAllOrderedMaintenances() {
        return getAllAssignments().stream()
                .map(RoomAssignment::getMaintenances)
                .filter(maintenances -> !(maintenances.isEmpty()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Maintenance> getAllMaintenancesOfCertainGuest(Long guestId) {
        Guest guestToViewMaintenances = guestDao.getById(guestId);
        if (guestToViewMaintenances == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to get all maintenances of certain guest");
            throw new IllegalArgumentException("Guest not found!");
        } else {
            List<Maintenance> allMaintenancesOfCertainGuest = new ArrayList<>();
            for (RoomAssignment roomAssignment : guestToViewMaintenances.getActiveRoomAssignments()) {
                allMaintenancesOfCertainGuest.addAll(roomAssignment.getMaintenances());
            }
            return allMaintenancesOfCertainGuest;
        }
    }

    @Override
    public List<Maintenance> sortMaintenancesByOrderDate (Long guestId) {
        Guest guestToViewMaintenances = guestDao.getById(guestId);
        if (guestToViewMaintenances == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to sort maintenances by date of order");
            throw new IllegalArgumentException("Guest not found!");
        } else {
            List<Maintenance> maintenancesToSort = new ArrayList<>(getAllMaintenancesOfCertainGuest(guestToViewMaintenances.getId()));
            maintenancesToSort.sort(Comparator.comparing(Maintenance::getOrderDate));
            return maintenancesToSort;
        }
    }

    @Override
    public List<Room> sortRoomsByCheckOutDate() {
        return getAllAssignments().stream()
                .sorted(Comparator.comparing(RoomAssignment::getCheckOutDate))
                .map(RoomAssignment::getRoom)
                .collect(Collectors.toList());
    }

    @Override
    public void completeDeserialization() {
        for (RoomAssignment roomAssignment : roomAssignmentDao.getAll()) {
            roomAssignment.getRoom().setRoomAssignment(roomAssignment);
            roomAssignment.getGuest().setRoomAssignment(roomAssignment);
        }
    }

}