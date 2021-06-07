package com.company.hoteladministrator.service;

import com.company.hoteladministrator.api.dao.IRoomDao;
import com.company.hoteladministrator.api.service.IRoomAssignmentService;
import com.company.hoteladministrator.model.OrderedMaintenance;
import com.company.hoteladministrator.model.RoomAssignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Transactional
public class RoomAssignmentService implements IRoomAssignmentService {

    private final IRoomDao roomDao;

    @Autowired
    private RoomAssignmentService(IRoomDao roomDao) {
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