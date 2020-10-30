package edu.labIV.db;


import java.sql.*;

public class DataBaseConnection {

    private final String USERNAME = "hlhgkjat";
    private final String PASSWORD = "9K4eyR23HfFlgzzXWze2ES1AiSyoaHoT";
    private final String URL = "jdbc:postgresql://motty.db.elephantsql.com:5432/hlhgkjat";

    private Connection connection;

    private static DataBaseConnection instance;

    private DataBaseConnection() {
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
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

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
