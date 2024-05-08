package assignment.server.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class DataSource {
    public Configuration configuration = new Configuration();
    private HikariConfig config = new HikariConfig();
    private HikariDataSource ds;

    public String connectionUrl = "jdbc:"+
            configuration.DB_CONNECTION+"://"+
            configuration.SERVER_NAME+":"+
            configuration.PORT_NUMBER+"/"+
            configuration.DB_NAME;

    public DataSource() throws Exception {
//        try {
//            config.setJdbcUrl(connectionUrl);
//            config.setUsername("root");
//            config.setPassword("");
//            config.addDataSourceProperty("cachePrepStmts", "true");
//            config.addDataSourceProperty("prepStmtCacheSize", "250");
//            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//            ds = new HikariDataSource(config);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}