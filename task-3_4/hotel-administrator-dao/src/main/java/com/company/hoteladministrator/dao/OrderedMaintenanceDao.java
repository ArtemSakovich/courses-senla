package com.company.hoteladministrator.dao;

import com.company.hoteladministrator.api.dao.IOrderedMaintenanceDao;
import com.company.hoteladministrator.dao.generic.AbstractDao;
import com.company.hoteladministrator.model.OrderedMaintenance;
import org.springframework.stereotype.Repository;

@Repository
public class OrderedMaintenanceDao extends AbstractDao<OrderedMaintenance> implements IOrderedMaintenanceDao {
    @Override
    protected Class<OrderedMaintenance> getEntityClass() {
        return OrderedMaintenance.class;
    }
}
