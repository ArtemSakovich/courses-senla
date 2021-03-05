package com.company.dao;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IRoomAssignmentDao;
import com.company.api.dao.IRoomDao;
import com.company.model.Guest;

public class GuestDao extends AbstractDao<Guest> implements IGuestDao {
    private static IGuestDao instance;

    private GuestDao() {
    }

    public static IGuestDao getInstance() {
        if (instance == null) {
            instance = new GuestDao();
        }
        return instance;
    }

    @Override
    public void update(Guest updatedGuest) {
        Guest guest = getById((updatedGuest.getId()));
        guest.setName(updatedGuest.getName());
        guest.setSurname(updatedGuest.getSurname());
        guest.setAge(updatedGuest.getAge());
    }
}
