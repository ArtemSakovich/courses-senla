package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IRoomAssignmentDao;
import com.company.api.dao.IRoomDao;
import com.company.api.service.IRoomAssignmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.company.model.OrderedMaintenance;
import com.company.model.RoomAssignment;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class RoomAssignmentService implements IRoomAssignmentService {
    @Autowired
    private IRoomAssignmentDao roomAssignmentDao;
    @Autowired
    private IGuestDao guestDao;
    @Autowired
    private IRoomDao roomDao;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private static final Logger log = Logger.getLogger(RoomAssignmentService.class.getName());

    private RoomAssignmentService() {
    }

    @Override
    public Double getPricePerStay(RoomAssignment roomAssignment) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Double totalMaintenancePrice = 0.0;
        for (OrderedMaintenance maintenance : roomAssignment.getMaintenances()) {
            totalMaintenancePrice += maintenance.getMaintenance().getMaintenancePrice();
        }
        return roomDao.getById(session, roomAssignment.getRoom().getId()).getRoomPrice() * DAYS.between(roomAssignment.getCheckInDate().toLocalDateTime(),
                roomAssignment.getCheckOutDate().toLocalDateTime()) + totalMaintenancePrice;
    }

    @Override
    public List<String> getThreeLastRoomAssigmentDates(Long roomId) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        return roomAssignmentDao.getThreeLastRoomAssigmentDates(session, roomId);
    }

}