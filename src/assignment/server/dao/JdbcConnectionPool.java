package assignment.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcConnectionPool {
    List<Connection> availableConnections = new ArrayList<Connection>();

    public JdbcConnectionPool() {
        initializeConnectionPool();
    }

    private void initializeConnectionPool() {
        while (!checkIfConnectionPoolIsFull()) {
            availableConnections.add(createNewConnectionForPool());
        }
    }

    private synchronized boolean checkIfConnectionPoolIsFull() {
        final String MAX_POOL_SIZE = Configuration.getInstance().DB_MAX_CONNECTIONS;

        int maxPoolSize = Integer.parseInt(MAX_POOL_SIZE);

        if (availableConnections.size() < maxPoolSize) {
            return false;
        }

        return true;
    }

    //Creating a connection
    private Connection createNewConnectionForPool() {
        try {
            Configuration config = Configuration.getInstance();

            String connectionURL = "jdbc:" +
                    config.DB_CONNECTION + "://" +
                    config.SERVER_NAME + ":" +
                    config.PORT_NUMBER + "/" +
                    config.DB_NAME + "?zeroDateTimeBehavior=convertToNull";

            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(connectionURL, config.DB_USER, config.DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized Connection getConnectionFromPool() {
        Connection connection = null;
        if (availableConnections.size() > 0) {
            connection = (Connection) availableConnections.get(0);
            availableConnections.remove(0);
        }
        return connection;
    }

    public synchronized void returnConnectionToPool(Connection connection) {
        availableConnections.add(connection);
    }

}
