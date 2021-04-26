package com.company.dao;

import com.company.api.dao.IMaintenanceDao;
import com.company.injection.annotation.DependencyClass;
import com.company.model.Maintenance;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
@DependencyClass
public class MaintenanceDao extends AbstractDao<Maintenance> implements IMaintenanceDao {

    @Override
    public void update(Connection connection, Maintenance updatedMaintenance) {
        Maintenance maintenance = getById(connection, updatedMaintenance.getId());
        maintenance.setMaintenanceName(updatedMaintenance.getMaintenanceName());
        maintenance.setMaintenancePrice(updatedMaintenance.getMaintenancePrice());
    }

    @Override
    protected String getInsertQuery() {
        final String INSERT_QUERY = "INSERT INTO maintenance (maintenanceName, maintenancePrice, maintenanceSection) VALUES (?, ?, ?)";
        return INSERT_QUERY;
    }

    @Override
    protected String getUpdateQuery() {
        final String UPDATE_QUERY = "UPDATE maintenance SET maintenanceName = ?, maintenancePrice = ?, maintenanceSection = ? WHERE id = ?";
        return UPDATE_QUERY;
    }

    @Override
    protected PreparedStatement getPreparedStatementForInsertAndUpdate(PreparedStatement preparedStatement, Maintenance maintenance) throws SQLException {
        preparedStatement.setString(1, maintenance.getMaintenanceName());
        preparedStatement.setDouble(2, maintenance.getMaintenancePrice());
        preparedStatement.setString(3, maintenance.getMaintenanceSection().toString());
        return preparedStatement;
    }

    @Override
    protected BeanHandler<Maintenance> getEntityBeanHandler(Connection connection) {
        return new BeanHandler<>(Maintenance.class);
    }

    @Override
    protected BeanListHandler<Maintenance> getEntityBeanListHandler(Connection connection) {
        return new BeanListHandler<>(Maintenance.class);
    }

    @Override
    public String getDBTableName() {
        final String MAINTENANCE_TABLE_NAME = "maintenance";
        return MAINTENANCE_TABLE_NAME;
    }

    @Override
    public String getDBIdColumnName() {
        final String MAINTENANCE_ID_COLUMN_NAME = "id";
        return MAINTENANCE_ID_COLUMN_NAME;
    }

    @Override
    protected String getColumnNameForABCSort() {
        final String MAINTENANCE_COLUMN_NAME_FOR_ABC_SORT = "maintenanceSection";
        return MAINTENANCE_COLUMN_NAME_FOR_ABC_SORT;
    }

    @Override
    public List<Maintenance> getMaintenancesSortedByPrice(Connection connection) {
        final String SELECT_MAINTENANCES_SORTED_BY_PRICE_QUERY = "SELECT * FROM maintenance ORDER BY maintenancePrice ASC";
        return getAllInternal(connection, SELECT_MAINTENANCES_SORTED_BY_PRICE_QUERY);
    }

    @Override
    public List<Maintenance> getAllOrderedMaintenances(Connection connection) {
        final String SELECT_ORDERED_MAINTENANCES = "SELECT * FROM maintenance m INNER JOIN orderedMaintenances om ON m.id = om.maintenanceId";
        return getAllInternal(connection, SELECT_ORDERED_MAINTENANCES);
    }

    @Override
    public List<Maintenance> getAllMaintenancesOfCertainGuest(Connection connection, Long guestId) {
        final String SELECT_ALL_ORDERED_MAINTENANCES_OF_CERTAIN_GUEST = "SELECT * FROM maintenance m INNER JOIN orderedMaintenances om ON m.id = om.maintenanceid where roomassignmentid IN " +
                "(SELECT id FROM roomassignment WHERE guestid = " + guestId;
        return getAllInternal(connection, SELECT_ALL_ORDERED_MAINTENANCES_OF_CERTAIN_GUEST);
    }

    @Override
    public List<Maintenance> sortMaintenancesByOrderDate(Connection connection, Long guestId) {
        final String SELECT_ALL_ORDERED_MAINTENANCES_OF_CERTAIN_GUEST_SORTED_BY_ORDER_DATE = "SELECT * FROM maintenance m INNER JOIN orderedMaintenances om ON m.id = om.maintenanceid where roomassignmentid IN " +
                "(SELECT id FROM roomassignment WHERE guestid = " + guestId + " ORDER BY om.orderDate";
        return getAllInternal(connection, SELECT_ALL_ORDERED_MAINTENANCES_OF_CERTAIN_GUEST_SORTED_BY_ORDER_DATE);
    }

    @Override
    public List<Maintenance> sortMaintenancesByPrice(Connection connection, Long guestId) {
        final String SELECT_ALL_ORDERED_MAINTENANCES_OF_CERTAIN_GUEST_SORTED_BY_PRICE = "SELECT * FROM maintenance m INNER JOIN orderedMaintenances om ON m.id = om.maintenanceid where roomassignmentid IN " +
                "(SELECT id FROM roomassignment WHERE guestid = " + guestId + " ORDER BY m.maintenancePrice";
        return getAllInternal(connection, SELECT_ALL_ORDERED_MAINTENANCES_OF_CERTAIN_GUEST_SORTED_BY_PRICE);
    }
}
