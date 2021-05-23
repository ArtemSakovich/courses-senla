package com.company.controller;

import com.company.hoteladministrator.api.service.IMaintenanceService;
import com.company.hoteladministrator.model.dto.MaintenanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MaintenanceController {

    IMaintenanceService maintenanceService;

    @Autowired
    private MaintenanceController(IMaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping(value = "/maintenance")
    @ResponseBody
    public MaintenanceDto addMaintenance(@RequestBody MaintenanceDto maintenanceDto) {
        return maintenanceService.addMaintenance(maintenanceDto);
    }

    @GetMapping(value = "/maintenances")
    public List<MaintenanceDto> getAllMaintenances() {
        return maintenanceService.getAllMaintenances();
    }

    @GetMapping(value = "/ordered-maintenances/{guestId}")
    public List<MaintenanceDto> getAllOrderedMaintenancesOfCertainGuest(@PathVariable Long guestId) {
        return maintenanceService.getAllMaintenancesOfCertainGuest(guestId);
    }

    @GetMapping(value = "/sorted-ordered-maintenances/{guestId}/{paramToSort}")
    public List<MaintenanceDto> getAllSortedOrderedMaintenancesOfCertainGuest(@PathVariable Long guestId, @PathVariable String paramToSort) {
        return maintenanceService.getSortedMaintenancesOfCertainGuest(guestId, paramToSort);
    }

    @GetMapping(value = "/sorted-maintenances/{paramToSort}")
    public List<MaintenanceDto> getAllSortedMaintenances(@PathVariable String paramToSort) {
        return maintenanceService.getSortedMaintenances(paramToSort);
    }

    @PutMapping(value = "/maintenance")
    @ResponseBody
    public MaintenanceDto changeMaintenanceInfo(@RequestBody MaintenanceDto maintenanceDto) {
        return maintenanceService.changeMaintenanceInfo(maintenanceDto);
    }

    @GetMapping(value = "/ordered-maintenances")
    public List<MaintenanceDto> getAllOrderedMaintenances() {
        return maintenanceService.getAllOrderedMaintenances();
    }

    @GetMapping(value = "/maintenances/{maintenanceId}")
    public MaintenanceDto getMaintenanceById(@PathVariable Long maintenanceId) {
        return maintenanceService.getMaintenanceById(maintenanceId);
    }

    @DeleteMapping(value = "/maintenance/{maintenanceId}")
    public void deleteMaintenance(@PathVariable Long maintenanceId) {
        maintenanceService.deleteMaintenance(maintenanceId);
    }
}