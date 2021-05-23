package com.company.hoteladministrator.model.dto;


import com.company.hoteladministrator.model.enums.MaintenanceSection;

public class MaintenanceDto extends AbstractDto {

    private String maintenanceName;
    private Double maintenancePrice;
    private MaintenanceSection maintenanceSection;

    public String getMaintenanceName() {
        return maintenanceName;
    }

    public void setMaintenanceName(String maintenanceName) {
        this.maintenanceName = maintenanceName;
    }

    public Double getMaintenancePrice() {
        return maintenancePrice;
    }

    public void setMaintenancePrice(Double maintenancePrice) {
        this.maintenancePrice = maintenancePrice;
    }

    public MaintenanceSection getMaintenanceSection() {
        return maintenanceSection;
    }

    public void setMaintenanceSection(MaintenanceSection maintenanceSection) {
        this.maintenanceSection = maintenanceSection;
    }
}
