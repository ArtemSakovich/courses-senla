package com.company.util;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IMaintenanceDao;
import com.company.api.dao.IRoomAssignmentDao;
import com.company.api.dao.IRoomDao;
import com.company.api.service.IRoomAssignmentService;
import com.company.api.util.ISerializeService;
import com.company.configuration.annotation.ConfigClass;
import com.company.configuration.annotation.ConfigProperty;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;
import com.company.model.AllData;
import com.company.service.RoomService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

@ConfigClass
@DependencyClass
public class SerializeService implements ISerializeService {
    @ConfigProperty(configName = "hoteladministrator.properties", propertyName = "fileForSerializationName")
    private String fileForSerializationName;
    @DependencyComponent
    IRoomAssignmentService roomAssignmentService;
    @DependencyComponent
    private IGuestDao guestDao;
    @DependencyComponent
    private IMaintenanceDao maintenanceDao;
    @DependencyComponent
    private IRoomDao roomDao;
    @DependencyComponent
    private IRoomAssignmentDao roomAssignmentDao;

    private static final Logger log = Logger.getLogger(RoomService.class.getName());

    private SerializeService() {

    }

    @Override
    public void serialize() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileForSerializationName))){
            writer.write(createJSonStringToSerialize());
        } catch (IOException e) {
            log.log(Level.SEVERE, "File not found!");
        }
    }

    @Override
    public void deserialize() {
        Gson gson = new Gson();

        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(fileForSerializationName)) {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(
                    new InputStreamReader(input, StandardCharsets.UTF_8));
            AllData allData = gson.fromJson(String.valueOf(jsonObject), AllData.class);
            guestDao.saveAll(allData.getAllGuests());
            maintenanceDao.saveAll(allData.getAllMaintenances());
            roomDao.saveAll(allData.getAllRooms());
            roomAssignmentDao.saveAll(allData.getAllRoomAssignments());
            roomAssignmentService.completeDeserialization();
        } catch (IOException | ParseException e) {
            log.log(Level.SEVERE, "File not found!");
        }
    }

    private String createJSonStringToSerialize() {
        AllData allData = new AllData();

        allData.setAllGuests(guestDao.getAll());
        allData.setAllMaintenances(maintenanceDao.getAll());
        allData.setAllRooms(roomDao.getAll());
        allData.setAllRoomAssignments(roomAssignmentDao.getAll());

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        return gson.toJson(allData);
    }
}
