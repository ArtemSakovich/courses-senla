package com.company.model;

import java.time.LocalDate;

public class Maintenance extends AEntity {

    private String maintenanceName;
    private Double maintenancePrice;
    private MaintenanceSection maintenanceSection;
    private LocalDate orderDate;

    public Maintenance(String maintenanceName, Double maintenancePrice, MaintenanceSection maintenanceSection) {
        this.maintenanceName = maintenanceName;
        this.maintenancePrice = maintenancePrice;
        this.maintenanceSection = maintenanceSection;
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

    public MaintenanceSection getMaintenanceSection() {
        return maintenanceSection;
    }

    public String getMaintenanceSectionAsString() {
        return maintenanceSection.toString();
    }

    public void setMaintenanceSection(MaintenanceSection maintenanceSection) {
        this.maintenanceSection = maintenanceSection;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
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
        return "Section: " + maintenanceSection + "; Maintenance #" + getId() + "; Name: " + maintenanceName +
                "; Price: " + maintenancePrice;
    }
}