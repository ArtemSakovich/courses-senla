package com.company.api.dao;

import com.company.model.Guest;

import java.util.List;

public interface IGuestDao extends IGenericDao<Guest> {
    List<Guest> getLastGuests(Long roomId, int allowedNumberOfNotes);
}
