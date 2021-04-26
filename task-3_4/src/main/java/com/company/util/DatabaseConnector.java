package com.company.util;

import com.company.api.exceptions.OperationCancelledException;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;
import org.postgresql.ds.PGPoolingDataSource;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@DependencyClass
public class DatabaseConnector {
    @DependencyComponent
    private PropertiesService propertiesService;
    private PGPoolingDataSource source;
    private static final Logger log = Logger.getLogger(DatabaseConnector.class.getName());

    private DatabaseConnector() {

    }

    public Connection getConnection() {
        Connection conn = null;
        if (source == null) {
            source = new PGPoolingDataSource();
            source.setServerName(propertiesService.getPropertyValueByKey("serverName"));
            source.setDatabaseName(propertiesService.getPropertyValueByKey("databaseName"));
            source.setMaxConnections(Integer.parseInt(propertiesService.getPropertyValueByKey("maxConnections")));
        }
        try {
            conn = source.getConnection();
            conn.setAutoCommit(false);
            return conn;
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Couldn't get connection.");
            throw new OperationCancelledException("Connection was received.");
        }
    }
}
