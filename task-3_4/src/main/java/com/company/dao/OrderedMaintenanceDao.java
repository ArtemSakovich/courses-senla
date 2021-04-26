package com.company.dao;

import com.company.api.dao.IOrderedMaintenanceDao;
import com.company.injection.annotation.DependencyClass;
import com.company.model.OrderedMaintenance;

@DependencyClass
public class OrderedMaintenanceDao extends AbstractDao<OrderedMaintenance> implements IOrderedMaintenanceDao {
    @Override
    protected Class<OrderedMaintenance> getEntityClass() {
        return OrderedMaintenance.class;
    }

    @Override
    protected String getColumnNameForABCSort() {
        return null;
    }
}
