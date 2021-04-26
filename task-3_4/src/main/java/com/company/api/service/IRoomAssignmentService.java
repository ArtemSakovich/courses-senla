package com.company.api.service;

import com.company.model.*;

import java.util.List;

public interface IRoomAssignmentService {

    Double getPricePerStay(RoomAssignment roomAssignment);

    List<String> getThreeLastRoomAssigmentDates(Long roomId);
}