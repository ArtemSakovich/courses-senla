package com.company.util.comparators.roomComparators;

import com.company.model.Room;
import com.company.model.RoomAssignment;
import com.company.model.RoomAssignmentStatus;

import java.time.LocalDate;
import java.util.Comparator;

public class CheckOutDateComparator implements Comparator<Room> {
    @Override
    public int compare(Room r1, Room r2) {
        LocalDate firstRoomEarliestDate = LocalDate.now();
        for (RoomAssignment roomAssignment : r1.getRoomAssignments()) {
            if (roomAssignment.getRoomAssignmentStatus().equals(RoomAssignmentStatus.ACTIVE) &&
                    roomAssignment.getCheckOutDate().isAfter(firstRoomEarliestDate)) {
                firstRoomEarliestDate = roomAssignment.getCheckOutDate();
            }
        }

        LocalDate secondRoomEarliestDate = LocalDate.now();
        for (RoomAssignment roomAssignment : r2.getRoomAssignments()) {
            if (roomAssignment.getRoomAssignmentStatus().equals(RoomAssignmentStatus.ACTIVE) &&
                    roomAssignment.getCheckOutDate().isAfter(secondRoomEarliestDate)) {
                secondRoomEarliestDate = roomAssignment.getCheckOutDate();
            }
        }
        return firstRoomEarliestDate.compareTo(secondRoomEarliestDate);
    }
}
