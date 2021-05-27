package com.company.hoteladministrator.controller;

import com.company.hoteladministrator.api.service.IMaintenanceService;
import com.company.hoteladministrator.model.dto.MaintenanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    @Autowired
    private MaintenanceController(IMaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping(value = "/admin/maintenance")
    @ResponseBody
    public MaintenanceDto addMaintenance(@RequestBody MaintenanceDto maintenanceDto) {
        return maintenanceService.addMaintenance(maintenanceDto);
    }

    @GetMapping(value = "/user/maintenances")
    public List<MaintenanceDto> getAllMaintenances() {
        return maintenanceService.getAllMaintenances();
    }

    @GetMapping(value = "/user/ordered-maintenances/{guestId}")
    public List<MaintenanceDto> getAllOrderedMaintenancesOfCertainGuest(@PathVariable Long guestId) {
        return maintenanceService.getAllMaintenancesOfCertainGuest(guestId);
    }

    @GetMapping(value = "/user/sorted-ordered-maintenances/{guestId}/{paramToSort}")
    public List<MaintenanceDto> getAllSortedOrderedMaintenancesOfCertainGuest(@PathVariable Long guestId, @PathVariable String paramToSort) {
        return maintenanceService.getSortedMaintenancesOfCertainGuest(guestId, paramToSort);
    }

    @GetMapping(value = "/user/sorted-maintenances/{paramToSort}")
    public List<MaintenanceDto> getAllSortedMaintenances(@PathVariable String paramToSort) {
        return maintenanceService.getSortedMaintenances(paramToSort);
    }

    @PutMapping(value = "/admin/maintenance")
    @ResponseBody
    public MaintenanceDto changeMaintenanceInfo(@RequestBody MaintenanceDto maintenanceDto) {
        return maintenanceService.changeMaintenanceInfo(maintenanceDto);
    }

    @GetMapping(value = "/user/ordered-maintenances")
    public List<MaintenanceDto> getAllOrderedMaintenances() {
        return maintenanceService.getAllOrderedMaintenances();
    }

    @GetMapping(value = "/user/maintenances/{maintenanceId}")
    public MaintenanceDto getMaintenanceById(@PathVariable Long maintenanceId) {
        return maintenanceService.getMaintenanceById(maintenanceId);
    }

    @DeleteMapping(value = "/admin/maintenance/{maintenanceId}")
    public void deleteMaintenance(@PathVariable Long maintenanceId) {
        maintenanceService.deleteMaintenance(maintenanceId);
    }
}