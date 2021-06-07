package com.company.hoteladministrator.api.service;

import com.company.hoteladministrator.model.dto.MaintenanceDto;

import java.util.List;

public interface IMaintenanceService {

    MaintenanceDto addMaintenance(MaintenanceDto maintenanceDto);

    List<MaintenanceDto> getAllMaintenances();

    MaintenanceDto changeMaintenanceInfo(MaintenanceDto maintenanceDto);

    List<MaintenanceDto> getSortedMaintenances(String paramToSort);

    List<MaintenanceDto> getAllMaintenancesOfCertainGuest(Long guestId);

    MaintenanceDto getMaintenanceById(Long id);

    List<MaintenanceDto> getSortedMaintenancesOfCertainGuest(Long guestId, String paramToSort);

    List<MaintenanceDto> getAllOrderedMaintenances();

    void deleteMaintenance(Long maintenanceId);
}
