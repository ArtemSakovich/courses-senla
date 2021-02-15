package com.company.util.comparators.maintenanceComparators;

import com.company.model.Maintenance;

import java.util.Comparator;

public class MaintenancePriceComparator implements Comparator<Maintenance> {
    @Override
    public int compare(Maintenance m1, Maintenance m2) {
        return (int) (m1.getMaintenancePrice() - m2.getMaintenancePrice());
    }
}
