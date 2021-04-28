package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IRoomAssignmentDao;
import com.company.api.dao.IRoomDao;
import com.company.api.service.IRoomAssignmentService;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;
import com.company.model.Maintenance;
import com.company.model.OrderedMaintenance;
import com.company.model.RoomAssignment;
import com.company.util.DatabaseConnector;
import com.company.util.HibernateSessionFactory;
import org.hibernate.Session;

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
    @DependencyComponent
    private HibernateSessionFactory hibernateSessionFactory;

    private static final Logger log = Logger.getLogger(RoomAssignmentService.class.getName());

    private RoomAssignmentService() {
    }

    @Override
    public Double getPricePerStay(RoomAssignment roomAssignment) {
        Session session = hibernateSessionFactory.openSession();
        Double totalMaintenancePrice = 0.0;
        for (OrderedMaintenance maintenance : roomAssignment.getMaintenances()) {
            totalMaintenancePrice += maintenance.getMaintenance().getMaintenancePrice();
        }
        return roomDao.getById(session, roomAssignment.getRoom().getId()).getRoomPrice() * DAYS.between(roomAssignment.getCheckInDate().toLocalDateTime(),
                roomAssignment.getCheckOutDate().toLocalDateTime()) + totalMaintenancePrice;
    }

    @Override
    public List<String> getThreeLastRoomAssigmentDates(Long roomId) {
        Session session = hibernateSessionFactory.openSession();
        return roomAssignmentDao.getThreeLastRoomAssigmentDates(session, roomId);
    }

}