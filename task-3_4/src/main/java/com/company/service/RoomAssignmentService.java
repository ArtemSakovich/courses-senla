package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IRoomAssignmentDao;
import com.company.api.dao.IRoomDao;
import com.company.api.service.IRoomAssignmentService;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;
import com.company.model.Maintenance;
import com.company.model.RoomAssignment;
import com.company.util.DatabaseConnector;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;

import static java.time.temporal.ChronoUnit.DAYS;

@DependencyClass
public class RoomAssignmentService implements IRoomAssignmentService {
    @DependencyComponent
    private IRoomAssignmentDao roomAssignmentDao;
    @DependencyComponent
    private IGuestDao guestDao;
    @DependencyComponent
    private IRoomDao roomDao;
    @DependencyComponent
    private DatabaseConnector databaseConnector;
    private static final Logger log = Logger.getLogger(RoomAssignmentService.class.getName());

    private RoomAssignmentService() {
    }

    @Override
    public Double getPricePerStay(RoomAssignment roomAssignment) {
        Connection connection = databaseConnector.getConnection();
        Double totalMaintenancePrice = 0.0;
        for (Maintenance maintenance : roomAssignment.getMaintenances()) {
            totalMaintenancePrice += maintenance.getMaintenancePrice();
        }
        return roomDao.getById(connection, roomAssignment.getRoomId()).getRoomPrice() * DAYS.between(roomAssignment.getCheckInDate().toLocalDateTime(),
                roomAssignment.getCheckOutDate().toLocalDateTime()) + totalMaintenancePrice;
    }

    @Override
    public List<String> getThreeLastRoomAssigmentDates(Long roomId) {
        Connection connection = databaseConnector.getConnection();
        return roomAssignmentDao.getThreeLastRoomAssigmentDates(connection, roomId);
    }

}