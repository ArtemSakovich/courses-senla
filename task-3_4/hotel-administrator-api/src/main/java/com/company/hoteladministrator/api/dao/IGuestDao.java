package com.company.hoteladministrator.api.dao;

import com.company.hoteladministrator.model.Guest;

import java.util.List;

public interface IGuestDao extends IGenericDao<Guest> {
    List<Guest> getLastGuests(Long roomId, int allowedNumberOfNotes);
}
