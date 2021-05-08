package com.company.dao;

import com.company.api.dao.IOrderedMaintenanceDao;
import com.company.model.OrderedMaintenance;
import org.springframework.stereotype.Repository;

@Repository
public class OrderedMaintenanceDao extends AbstractDao<OrderedMaintenance> implements IOrderedMaintenanceDao {
    @Override
    protected Class<OrderedMaintenance> getEntityClass() {
        return OrderedMaintenance.class;
    }
}
