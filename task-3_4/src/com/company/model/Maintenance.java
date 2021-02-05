package com.company.model;

import com.company.util.IdGenerator;

public class Maintenance extends AEntity {
    private String maintenanceName;
    private Double maintenancePrice;
    private Long id;

    public Maintenance(String maintenanceName, double maintenancePrice) {
        this.maintenanceName = maintenanceName;
        this.maintenancePrice = maintenancePrice;
    }

    public String getMaintenanceName() {
        return maintenanceName;
    }

    public Double getMaintenancePrice() {
        return maintenancePrice;
    }

    public void setMaintenanceName(String maintenanceName) {
        this.maintenanceName = maintenanceName;
    }

    public void setMaintenancePrice(Double maintenancePrice) {
        this.maintenancePrice = maintenancePrice;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + maintenanceName.hashCode();
        result = (int) (31 * result + maintenancePrice);
        return result;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Maintenance)) {
            return false;
        }

        Maintenance maintenance = (Maintenance) o;

        return maintenance.maintenanceName.equals(maintenanceName) &&
                maintenance.maintenancePrice.equals(maintenancePrice);
    }

    @Override
    public String toString() {
        return "Maintenance #" + getId() + ". Name: " + maintenanceName +
                ". Price: " + maintenancePrice;
    }
}