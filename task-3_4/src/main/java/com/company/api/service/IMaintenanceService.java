package com.company.api.service;

import com.company.dto.MaintenanceDto;

import java.util.List;

public interface IMaintenanceService {

    MaintenanceDto addMaintenance(MaintenanceDto maintenanceDto);

    List<MaintenanceDto> getAllMaintenances();

    MaintenanceDto changeMaintenanceInfo(MaintenanceDto maintenanceDto);

    List<MaintenanceDto> getSortedMaintenances(String paramToSort);

    List<MaintenanceDto> getAllMaintenancesOfCertainGuest(Long guestId);

    List<MaintenanceDto> getSortedMaintenancesOfCertainGuest(Long guestId, String paramToSort);

    List<MaintenanceDto> getAllOrderedMaintenances();

    void deleteMaintenance(Long maintenanceId);
}
