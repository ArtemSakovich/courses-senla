package com.company.util.comparators.maintenanceComparators;

import com.company.model.Maintenance;

import java.time.LocalDate;
import java.util.Comparator;

public class MaintenanceOrderDateComparator implements Comparator<Maintenance> {

    @Override
    public int compare(Maintenance m1, Maintenance m2) {
        return m1.getOrderDate().compareTo(m2.getOrderDate());
    }
}
