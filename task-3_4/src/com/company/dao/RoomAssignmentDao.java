package com.company.dao;

import com.company.api.dao.IRoomAssignmentDao;
import com.company.model.RoomAssignment;

public class RoomAssignmentDao extends AbstractDao<RoomAssignment> implements IRoomAssignmentDao {
    private static IRoomAssignmentDao instance;

    private RoomAssignmentDao() {
    }

    public static IRoomAssignmentDao getInstance() {
        if (instance == null) {
            instance = new RoomAssignmentDao();
        }
        return instance;
    }

    @Override
    public void update(RoomAssignment updatedRoomAssignment) {
        RoomAssignment roomAssignment = getById((updatedRoomAssignment.getId()));
        roomAssignment.setRoom(updatedRoomAssignment.getRoom());
        roomAssignment.setRoomAssignmentStatus(updatedRoomAssignment.getRoomAssignmentStatus());
        roomAssignment.setCheckInDate(updatedRoomAssignment.getCheckInDate());
        roomAssignment.setGuest(updatedRoomAssignment.getGuest());
        roomAssignment.setCheckOutDate(updatedRoomAssignment.getCheckOutDate());
    }


}
