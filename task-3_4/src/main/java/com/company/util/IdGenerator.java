package com.company.util;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IMaintenanceDao;
import com.company.api.dao.IRoomAssignmentDao;
import com.company.api.dao.IRoomDao;
import com.company.dao.GuestDao;
import com.company.dao.MaintenanceDao;
import com.company.dao.RoomAssignmentDao;
import com.company.dao.RoomDao;

public class IdGenerator {
    private static IdGenerator instance;
    private final IGuestDao guestDao = GuestDao.getInstance();
    private final IMaintenanceDao maintenanceDao = MaintenanceDao.getInstance();
    private final IRoomDao roomDao = RoomDao.getInstance();
    private final IRoomAssignmentDao roomAssignmentDao = RoomAssignmentDao.getInstance();

    private Long guestId = 1L + guestDao.getMaxId();
    private Long roomId = 1L + roomDao.getMaxId();
    private Long maintenanceId = 1L + maintenanceDao.getMaxId();
    private Long roomAssignmentId = roomAssignmentDao.getMaxId();

    public static IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    public Long generateGuestId() {
        return guestId ++;
    }

    public Long generateRoomId() {
        return roomId ++;
    }

    public Long generateMaintenanceId() {
        return maintenanceId ++;
    }

    public Long generateRoomAssignmentId() {
        return roomAssignmentId ++;
    }
}
