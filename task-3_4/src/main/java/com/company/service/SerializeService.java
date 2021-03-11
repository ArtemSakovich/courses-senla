package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IMaintenanceDao;
import com.company.api.dao.IRoomAssignmentDao;
import com.company.api.dao.IRoomDao;
import com.company.api.service.ISerializeService;
import com.company.dao.GuestDao;
import com.company.dao.MaintenanceDao;
import com.company.dao.RoomAssignmentDao;
import com.company.dao.RoomDao;
import com.company.model.AllData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerializeService implements ISerializeService {
    private static ISerializeService instance;
    private String fileName;

    private final IGuestDao guestDao;
    private final IMaintenanceDao maintenanceDao;
    private final IRoomDao roomDao;
    private final IRoomAssignmentDao roomAssignmentDao;

    Logger log = Logger.getLogger(RoomService.class.getName());

    private SerializeService() {
        this.guestDao = GuestDao.getInstance();
        this.maintenanceDao = MaintenanceDao.getInstance();
        this.roomDao = RoomDao.getInstance();
        this.roomAssignmentDao = RoomAssignmentDao.getInstance();

        try (FileInputStream input = new FileInputStream("hotelAdministrator.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            this.fileName = properties.getProperty("fileNameForSerializeService");
        } catch (IOException e) {
            log.log(Level.SEVERE, "Property file not found");
        }
    }

    public static ISerializeService getInstance() {
        if (instance == null) {
            instance = new SerializeService();
        }
        return instance;
    }
    @Override
    public void serialize() {
        AllData allData = new AllData();

        allData.setAllGuests(guestDao.getAll());
        allData.setAllMaintenances(maintenanceDao.getAll());
        allData.setAllRooms(roomDao.getAll());
        allData.setAllRoomAssignments(roomAssignmentDao.getAll());

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(allData);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            writer.write(json);
        } catch (IOException e) {
            log.log(Level.SEVERE, "File not found!");
        }
    }

    @Override
    public void deserialize() {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(fileName)) {
            AllData allData = gson.fromJson(reader, AllData.class);
            guestDao.saveAll(allData.getAllGuests());
            maintenanceDao.saveAll(allData.getAllMaintenances());
            roomDao.saveAll(allData.getAllRooms());
            roomAssignmentDao.saveAll(allData.getAllRoomAssignments());
            RoomAssignmentService.getInstance().completeDeserialization();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
