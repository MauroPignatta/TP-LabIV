package edu.labIV.db;

import edu.labIV.entity.Account;

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

    public boolean insertAccountQuery(Account account){
        String sql = "INSERT INTO account(username, email, password, active) VALUES(?,?,?,?)";
        boolean executed = false;
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getEmail());
            statement.setString(3, account.getPassword());
            statement.setBoolean(4, account.isActive());
            executed = statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return executed;
    }

    public Account selectAccountQuery(String username){
        String sql = "SELECT * FROM account WHERE username = '"+username+"'";
        Account account = new Account();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setEmail(resultSet.getString("email" ));
                account.setActive(resultSet.getBoolean("active"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}
