package com.company.hoteladministrator.service;

import com.company.hoteladministrator.api.dao.IMaintenanceDao;
import com.company.hoteladministrator.api.mapper.IMaintenanceMapper;
import com.company.hoteladministrator.api.service.IMaintenanceService;
import com.company.hoteladministrator.model.Maintenance;
import com.company.hoteladministrator.model.dto.MaintenanceDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MaintenanceService implements IMaintenanceService {

    private final IMaintenanceDao maintenanceDao;
    private final IMaintenanceMapper maintenanceMapper;

    private static final Logger log = Logger.getLogger(MaintenanceService.class.getName());

    @Autowired
    private MaintenanceService(IMaintenanceDao maintenanceDao, IMaintenanceMapper maintenanceMapper) {
        this.maintenanceDao = maintenanceDao;
        this.maintenanceMapper = maintenanceMapper;
    }

    @Override
    public MaintenanceDto addMaintenance(MaintenanceDto maintenanceDto) {
        Maintenance entity = maintenanceMapper.toEntity(maintenanceDto);
        maintenanceDao.save(entity);
        return maintenanceMapper.toDto(entity);
    }

    @Override
    public List<MaintenanceDto> getAllMaintenances() {
        return maintenanceDao.getAll().stream()
                .map(maintenanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MaintenanceDto changeMaintenanceInfo(MaintenanceDto maintenanceDto) {
        Maintenance maintenanceToChange = maintenanceDao.getById(maintenanceDto.getId());
        if (maintenanceToChange == null) {
            log.warn("Incorrect input when trying to change maintenance price");
            throw new IllegalArgumentException("Maintenance not found!");
        } else {
            maintenanceToChange.setMaintenanceName(maintenanceDto.getMaintenanceName());
            maintenanceToChange.setMaintenancePrice(maintenanceDto.getMaintenancePrice());
            maintenanceToChange.setMaintenanceSection(maintenanceDto.getMaintenanceSection());
            maintenanceDao.update(maintenanceToChange);
        }
        return maintenanceMapper.toDto(maintenanceDao.getById(maintenanceDto.getId()));
    }

    @Override
    public List<MaintenanceDto> getSortedMaintenances(String paramToSort) {
        return maintenanceDao.getSortedMaintenances(paramToSort).stream()
                .map(maintenanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MaintenanceDto getMaintenanceById(Long id) {
        return maintenanceMapper.toDto(maintenanceDao.getById(id));
    }

    @Override
    public List<MaintenanceDto> getSortedMaintenancesOfCertainGuest(Long guestId, String paramToSort) {
        return maintenanceDao.getSortedMaintenancesOfCertainGuest(guestId, paramToSort).stream()
                .map(orderedMaintenance -> maintenanceMapper.toDto(maintenanceDao.getById(orderedMaintenance.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<MaintenanceDto> getAllMaintenancesOfCertainGuest(Long guestId) {
        return maintenanceDao.getAllMaintenancesOfCertainGuest(guestId).stream()
                .map(orderedMaintenance -> maintenanceMapper.toDto(maintenanceDao.getById(orderedMaintenance.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<MaintenanceDto> getAllOrderedMaintenances() {
        return maintenanceDao.getAllOrderedMaintenances().stream()
                .map(maintenanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMaintenance(Long maintenanceId) {
        maintenanceDao.delete(maintenanceDao.getById(maintenanceId));
    }
}