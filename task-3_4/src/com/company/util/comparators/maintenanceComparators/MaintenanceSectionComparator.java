package com.company.util.comparators.maintenanceComparators;

import com.company.model.Maintenance;

import java.util.Comparator;

public class MaintenanceSectionComparator implements Comparator<Maintenance> {
    @Override
    public int compare(Maintenance m1, Maintenance m2) {
        return m1.getMaintenanceSection().compareTo(m2.getMaintenanceSection());
    }
}
