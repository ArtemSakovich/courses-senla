package com.company.api.service;

import com.company.model.*;

import java.util.List;

public interface IRoomAssignmentService {
    void update(RoomAssignment updatedRoomAssignment);

    RoomAssignment getById(Long id);

    public List<RoomAssignment> getAllAssignments();

    public List<List<Maintenance>> getAllOrderedMaintenances();

    public List<Maintenance> getAllMaintenancesOfCertainGuest(Long guestId);

    public List<Maintenance> sortMaintenancesByOrderDate (Long guestId);

    public List<Room> sortRoomsByCheckOutDate();

}