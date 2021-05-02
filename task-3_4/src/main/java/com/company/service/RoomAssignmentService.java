package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IRoomAssignmentDao;
import com.company.api.dao.IRoomDao;
import com.company.api.service.IRoomAssignmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.company.model.OrderedMaintenance;
import com.company.model.RoomAssignment;
import org.springframework.stereotype.Service;
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

    private static final Logger log = Logger.getLogger(RoomAssignmentService.class.getName());

    private RoomAssignmentService() {
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

    @Override
    public List<String> getThreeLastRoomAssigmentDates(Long roomId) {
        return roomAssignmentDao.getThreeLastRoomAssigmentDates(roomId);
    }

}