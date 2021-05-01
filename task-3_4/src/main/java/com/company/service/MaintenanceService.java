package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IMaintenanceDao;
import com.company.api.dao.IRoomAssignmentDao;
import com.company.api.service.IMaintenanceService;
import com.company.model.Maintenance;
import com.company.model.MaintenanceSection;
import com.company.model.OrderedMaintenance;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class MaintenanceService implements IMaintenanceService {
    @Autowired
    private IMaintenanceDao maintenanceDao;
    @Autowired
    private IGuestDao guestDao;
    @Autowired
    private IRoomAssignmentDao roomAssignmentDao;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private static final Logger log = Logger.getLogger(MaintenanceService.class.getName());

    private MaintenanceService() {

    }

    @Override
    @Transactional
    public Maintenance addMaintenance(String name, Double price, MaintenanceSection section) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Maintenance maintenance = new Maintenance(name, price, section);
        maintenanceDao.save(session, maintenance);
        return maintenance;
    }

    @Override
    public void changeMaintenancePrice(Long id, Double newPrice) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Maintenance maintenanceToChangePrice = maintenanceDao.getById(session, id);
        if (maintenanceToChangePrice == null) {
            log.warn("Incorrect input when trying to change maintenance price");
            throw new IllegalArgumentException("Maintenance not found!");
        } else {
            maintenanceToChangePrice.setMaintenancePrice(newPrice);
            maintenanceDao.update(session, maintenanceToChangePrice);
        }
    }

    @Override
    public List<Maintenance> getSortedMaintenances(String paramToSort) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        return maintenanceDao.getSortedMaintenances(session, paramToSort);
    }

    @Override
    public List<Maintenance> getSortedMaintenancesOfCertainGuest(Long guestId, String paramToSort) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        return maintenanceDao.getSortedMaintenancesOfCertainGuest(session, guestId, paramToSort).stream()
                .map(OrderedMaintenance::getMaintenance).collect(Collectors.toList());
    }

    @Override
    public List<Maintenance> getAllMaintenancesOfCertainGuest(Long guestId) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        return maintenanceDao.getAllMaintenancesOfCertainGuest(session, guestId).stream()
                .map(OrderedMaintenance::getMaintenance).collect(Collectors.toList());
    }

    @Override
    public List<Maintenance> getAllOrderedMaintenances() {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        return maintenanceDao.getAllOrderedMaintenances(session);
    }
}