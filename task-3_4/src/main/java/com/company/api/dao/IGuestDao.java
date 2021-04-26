package com.company.api.dao;

import com.company.model.Guest;

import java.sql.Connection;
import java.util.List;

public interface IGuestDao extends IGenericDao<Guest> {
    List<Guest> getThreeLastGuests(Connection connection, Long roomId);
}
