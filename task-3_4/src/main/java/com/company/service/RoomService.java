package com.company.service;

import com.company.api.dao.IRoomDao;
import com.company.api.exceptions.OperationCancelledException;
import com.company.api.service.IRoomService;
import com.company.configuration.annotation.ConfigClass;
import com.company.configuration.annotation.ConfigProperty;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;
import com.company.model.Room;
import com.company.model.RoomStatus;
import com.company.util.DatabaseConnector;
import com.company.util.PropertiesService;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ConfigClass
@DependencyClass
public class RoomService implements IRoomService {

    @DependencyComponent
    private IRoomDao roomDao;
    @ConfigProperty(configName = "hoteladministrator.properties", propertyName = "isRoomStatusChangeable")
    private boolean isRoomStatusChangeable;
    @ConfigProperty(configName = "hoteladministrator.properties", propertyName = "allowedNumberOfNotes")
    private int allowedNumberOfNotes;
    @DependencyComponent
    private DatabaseConnector databaseConnector;
    private static final Logger log = Logger.getLogger(RoomService.class.getName());

    private RoomService() {

    }

    @Override
    public Room addRoom(Integer roomNumber, Double roomPrice, Integer numberOfBeds, Integer numberOfStars) {
        Connection connection = databaseConnector.getConnection();
        Room room = new Room(roomNumber, roomPrice, numberOfBeds, numberOfStars);
        room.setRoomStatus(RoomStatus.FREE);
        roomDao.save(connection,room);
        return room;
    }

    @Override
    public List<Room> getAllRooms() {
        Connection connection = databaseConnector.getConnection();
        return roomDao.getAll(connection);
    }

    @Override
    public Room getById(Long roomId) {
        Connection connection = databaseConnector.getConnection();
        return roomDao.getById(connection, roomId);
    }

    @Override
    public void changeRoomStatus(Long id, RoomStatus newRoomStatus) {
        Connection connection = databaseConnector.getConnection();
        if (isRoomStatusChangeable) {
            Room roomToChangeStatus = roomDao.getById(connection, id);
            if (roomToChangeStatus == null) {
                log.log(Level.SEVERE, "Incorrect input when trying to change room status");
                throw new IllegalArgumentException("Room not found!");
            } else {
                roomToChangeStatus.setRoomStatus(newRoomStatus);
                try {
                    roomDao.update(connection, roomToChangeStatus);
                } catch (SQLException e) {
                    log.log(Level.SEVERE, "Connection was interrupted. Data has not been updated.");
                    throw new OperationCancelledException("Connection was interrupted. Data has not been updated.");
                }
            }
        } else {
            log.log(Level.SEVERE, "Error when trying to change room status");
            throw new IllegalArgumentException("This function is not available at the moment");
        }
    }

    @Override
    public void changeRoomPrice(Long id, Double newRoomPrice) {
        Connection connection = databaseConnector.getConnection();
        Room roomToChangePrice = roomDao.getById(connection, id);
        if (roomToChangePrice == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to change room price");
            throw new IllegalArgumentException("Room not found!");
        } else {
            roomToChangePrice.setRoomPrice(newRoomPrice);
            try {
                roomDao.update(connection, roomToChangePrice);
            } catch (SQLException e) {
                log.log(Level.SEVERE, "Connection was interrupted. Data has not been updated.");
                throw new OperationCancelledException("Connection was interrupted. Data has not been updated.");
            }
        }
    }

    @Override
    public List<Room> getAllFreeRooms() {
        Connection connection = databaseConnector.getConnection();
        return roomDao.getFreeRooms(connection);
    }

    @Override
    public Integer getNumberOfFreeRooms() {
        return getAllFreeRooms().size();
    }

    @Override
    public List<Room> getFreeRoomsByDate(LocalDateTime requiredDate) {
        Connection connection = databaseConnector.getConnection();
        return roomDao.getFreeRoomsByDate(connection, requiredDate);
    }

    @Override
    public List<Room> sortRoomsByPrice() {
        Connection connection = databaseConnector.getConnection();
        return roomDao.getRoomsSortedByPrice(connection);
    }

    @Override
    public List<Room> sortRoomsByNumberOfBeds() {
        Connection connection = databaseConnector.getConnection();
        return roomDao.getRoomsSortedByNumberOfBeds(connection);
    }

    @Override
    public List<Room> sortRoomsByNumberOfStars() {
        Connection connection = databaseConnector.getConnection();
        return roomDao.getRoomsSortedByNumberOfStars(connection);
    }

    @Override
    public List<Room> sortFreeRoomsByPrice() {
        Connection connection = databaseConnector.getConnection();
        return roomDao.sortFreeRoomsByPrice(connection);
    }

    @Override
    public List<Room> sortFreeRoomsByNumberOfBeds() {
        Connection connection = databaseConnector.getConnection();
        return roomDao.sortFreeRoomsByNumberOfBeds(connection);
    }

    @Override
    public List<Room> sortFreeRoomsByNumberOfStars() {
        Connection connection = databaseConnector.getConnection();
        return roomDao.sortFreeRoomsByNumberOfStars(connection);
    }

    @Override
    public List<Room> sortRoomsByCheckOutDate() {
        Connection connection = databaseConnector.getConnection();
        return roomDao.sortRoomsByCheckOutDate(connection);
    }
}