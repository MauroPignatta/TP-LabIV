package edu.labIV.db;

import edu.labIV.cfg.*;

import java.sql.*;


public class DataBaseConnection {

    private static DataBaseConnection instance;

    private String username;
    private String password;
    private String url;

    private Connection connection;

    private DataBaseConnection() {
        username = Config.get(ConfigSection.DATABASE, ConfigKey.DB_USER);
        password = Config.get(ConfigSection.DATABASE, ConfigKey.DB_PASS);
        url = Config.get(ConfigSection.DATABASE, ConfigKey.DB_URL);
        connect();
    }

    public static DataBaseConnection getInstance(){
        if (instance == null){
            instance = new DataBaseConnection();
        }
        return instance;
    }

    private void connect(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement createPrepareStatement(String sql) throws SQLException{
        return connection.prepareStatement(sql);
    }

    public Statement createStatement() throws SQLException {
          return connection.createStatement();
    }
}
