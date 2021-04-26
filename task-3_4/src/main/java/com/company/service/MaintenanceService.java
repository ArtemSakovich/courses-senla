package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IMaintenanceDao;
import com.company.api.dao.IRoomAssignmentDao;
import com.company.api.exceptions.OperationCancelledException;
import com.company.api.service.IMaintenanceService;
import com.company.configuration.annotation.ConfigClass;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;
import com.company.model.Maintenance;
import com.company.model.MaintenanceSection;
import com.company.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@DependencyClass
public class MaintenanceService implements IMaintenanceService {
    @DependencyComponent
    private IMaintenanceDao maintenanceDao;
    @DependencyComponent
    private IGuestDao guestDao;
    @DependencyComponent
    private IRoomAssignmentDao roomAssignmentDao;
    @DependencyComponent
    private DatabaseConnector databaseConnector;
    private static final Logger log = Logger.getLogger(Maintenance.class.getName());

    private MaintenanceService() {

    }

    @Override
    public Maintenance addMaintenance(String name, Double price, MaintenanceSection section) {
        Connection connection = databaseConnector.getConnection();
        Maintenance maintenance = new Maintenance(name, price, section);
        maintenanceDao.save(connection,maintenance);
        return maintenance;
    }

    @Override
    public void changeMaintenancePrice(Long id, Double newPrice) {
        Connection connection = databaseConnector.getConnection();
        Maintenance maintenanceToChangePrice = maintenanceDao.getById(connection, id);
        if (maintenanceToChangePrice == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to change maintenance price");
            throw new IllegalArgumentException("Maintenance not found!");
        } else {
            maintenanceToChangePrice.setMaintenancePrice(newPrice);
            try {
                maintenanceDao.update(connection,maintenanceToChangePrice);
            } catch (SQLException e) {
                log.log(Level.SEVERE, "Connection was interrupted. Data has not been updated.");
                throw new OperationCancelledException("Connection was interrupted. Data has not been updated.");
            }
        }
    }

    @Override
    public List<Maintenance> sortAllMaintenancesByPrice() {
        Connection connection = databaseConnector.getConnection();
        return maintenanceDao.getMaintenancesSortedByPrice(connection);
    }

    @Override
    public List<Maintenance> sortAllMaintenancesBySectionABC() {
        Connection connection = databaseConnector.getConnection();
        return maintenanceDao.getSortedABCEntities(connection);
    }

    @Override
    public List<Maintenance> sortMaintenancesOfCertainGuestByPrice(Long guestId) {
        Connection connection = databaseConnector.getConnection();
        return maintenanceDao.sortMaintenancesByPrice(connection, guestId);
    }

    @Override
    public List<Maintenance> getAllMaintenancesOfCertainGuest(Long guestId) {
        Connection connection = databaseConnector.getConnection();
        return maintenanceDao.getAllMaintenancesOfCertainGuest(connection, guestId);
    }

    @Override
    public List<Maintenance> sortMaintenancesByOrderDate(Long guestId) {
        Connection connection = databaseConnector.getConnection();
        return maintenanceDao.sortMaintenancesByOrderDate(connection, guestId);
    }

    @Override
    public List<Maintenance> getAllOrderedMaintenances() {
        Connection connection = databaseConnector.getConnection();
        return maintenanceDao.getAllOrderedMaintenances(connection);
    }
}