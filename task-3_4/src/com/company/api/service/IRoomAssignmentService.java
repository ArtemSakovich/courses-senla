package com.company.api.service;

import com.company.model.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public interface IRoomAssignmentService {

    public List<RoomAssignment> getAllAssignments();

    public List<Maintenance> getAllOrderedMaintenances();

    public List<Maintenance> getAllMaintenancesOfCertainGuest(Guest guest);

}