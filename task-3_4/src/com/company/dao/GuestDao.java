package com.company.dao;

import com.company.api.dao.IGuestDao;
import com.company.model.Guest;

public class GuestDao extends AbstractDao<Guest> implements IGuestDao {
    @Override
    public void update(Guest updatedGuest) {
        Guest guest = getById((updatedGuest.getId()));
        guest.setName(updatedGuest.getName());
        guest.setSurname(updatedGuest.getSurname());
        guest.setAge(updatedGuest.getAge());
    }
}
