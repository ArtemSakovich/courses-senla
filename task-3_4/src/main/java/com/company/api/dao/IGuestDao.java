package com.company.api.dao;

import com.company.model.Guest;

import javax.persistence.EntityManager;
import java.util.List;

public interface IGuestDao extends IGenericDao<Guest> {
    List<Guest> getThreeLastGuests(EntityManager entityManager, Long roomId);
}
