package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IMaintenanceDao;
import com.company.api.dao.IRoomAssignmentDao;
import com.company.api.service.IMaintenanceService;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;
import com.company.model.Maintenance;
import com.company.model.MaintenanceSection;
import com.company.model.OrderedMaintenance;
import com.company.util.DatabaseConnector;
import com.company.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
    @DependencyComponent
    private HibernateSessionFactory hibernateSessionFactory;

    private static final Logger log = Logger.getLogger(Maintenance.class.getName());

    private MaintenanceService() {

    }

    @Override
    public Maintenance addMaintenance(String name, Double price, MaintenanceSection section) {
        Session session = hibernateSessionFactory.openSession();
        Maintenance maintenance = new Maintenance(name, price, section);
        Transaction tx1 = session.beginTransaction();
        maintenanceDao.save(session, maintenance);
        tx1.commit();
        session.close();
        return maintenance;
    }

    @Override
    public void changeMaintenancePrice(Long id, Double newPrice) {
        Session session = hibernateSessionFactory.openSession();
        Maintenance maintenanceToChangePrice = maintenanceDao.getById(session, id);
        if (maintenanceToChangePrice == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to change maintenance price");
            throw new IllegalArgumentException("Maintenance not found!");
        } else {
            maintenanceToChangePrice.setMaintenancePrice(newPrice);
            Transaction tx1 = session.beginTransaction();
            maintenanceDao.update(session, maintenanceToChangePrice);
            tx1.commit();
            session.close();
        }
    }

    @Override
    public List<Maintenance> sortAllMaintenancesByPrice() {
        Session session = hibernateSessionFactory.openSession();
        return maintenanceDao.getMaintenancesSortedByPrice(session);
    }

    @Override
    public List<Maintenance> sortAllMaintenancesBySectionABC() {
        Session session = hibernateSessionFactory.openSession();
        return maintenanceDao.getSortedABCEntities(session);
    }

    @Override
    public List<Maintenance> sortMaintenancesOfCertainGuestByPrice(Long guestId) {
        Session session = hibernateSessionFactory.openSession();
        return maintenanceDao.sortMaintenancesOfCertainGuestByPrice(session, guestId).stream()
                .map(OrderedMaintenance::getMaintenance).collect(Collectors.toList());
    }

    @Override
    public List<Maintenance> getAllMaintenancesOfCertainGuest(Long guestId) {
        Session session = hibernateSessionFactory.openSession();
        return maintenanceDao.getAllMaintenancesOfCertainGuest(session, guestId).stream()
                .map(OrderedMaintenance::getMaintenance).collect(Collectors.toList());
    }

    @Override
    public List<Maintenance> sortMaintenancesByOrderDate(Long guestId) {
        Session session = hibernateSessionFactory.openSession();
        return maintenanceDao.sortMaintenancesByOrderDate(session, guestId).stream()
                .map(OrderedMaintenance::getMaintenance).collect(Collectors.toList());
    }

    @Override
    public List<Maintenance> getAllOrderedMaintenances() {
        Session session = hibernateSessionFactory.openSession();
        return maintenanceDao.getAllOrderedMaintenances(session);
    }
}