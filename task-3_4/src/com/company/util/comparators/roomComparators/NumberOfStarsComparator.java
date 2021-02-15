package com.company.util.comparators.roomComparators;

import com.company.model.Room;

import java.util.Comparator;

public class NumberOfStarsComparator implements Comparator<Room> {
    @Override
    public int compare(Room r1, Room r2) {
        return r1.getNumberOfStars() - r2.getNumberOfStars();
    }
}
