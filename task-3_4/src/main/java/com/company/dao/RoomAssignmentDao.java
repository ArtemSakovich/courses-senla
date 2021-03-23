package com.company.dao;

import com.company.api.dao.IRoomAssignmentDao;
import com.company.injection.annotation.DependencyClass;
import com.company.model.RoomAssignment;
@DependencyClass
public class RoomAssignmentDao extends AbstractDao<RoomAssignment> implements IRoomAssignmentDao {

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
