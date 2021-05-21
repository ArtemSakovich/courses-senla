package com.company.service;

import com.company.api.dao.IRoomAssignmentDao;
import com.company.api.dao.IRoomDao;
import com.company.api.service.IRoomAssignmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.company.model.OrderedMaintenance;
import com.company.model.RoomAssignment;
import org.springframework.stereotype.Service;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class RoomAssignmentService implements IRoomAssignmentService {

    private IRoomAssignmentDao roomAssignmentDao;
    private IRoomDao roomDao;

    private static final Logger log = Logger.getLogger(RoomAssignmentService.class.getName());

    @Autowired
    private RoomAssignmentService(IRoomAssignmentDao roomAssignmentDao, IRoomDao roomDao) {
        this.roomAssignmentDao = roomAssignmentDao;
        this.roomDao = roomDao;
    }

    @Override
    public Double getPricePerStay(RoomAssignment roomAssignment) {
        Double totalMaintenancePrice = 0.0;
        for (OrderedMaintenance maintenance : roomAssignment.getMaintenances()) {
            totalMaintenancePrice += maintenance.getMaintenance().getMaintenancePrice();
        }
        return roomDao.getById(roomAssignment.getRoom().getId()).getRoomPrice() * DAYS.between(roomAssignment.getCheckInDate().toLocalDateTime(),
                roomAssignment.getCheckOutDate().toLocalDateTime()) + totalMaintenancePrice;
    }
}