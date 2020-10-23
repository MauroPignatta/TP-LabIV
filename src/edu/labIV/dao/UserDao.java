package edu.labIV.dao;

import edu.labIV.entity.Account;
import edu.labIV.entity.User;

import java.sql.*;
import java.time.LocalDate;

public class UserDao extends Dao<User> {

    private static final String USR_TABLE = "public.user";
    private static final String USR_ID = "account_id";
    private static final String USR_NAME = "name";
    private static final String USR_LAST_NAME = "last_name";
    private static final String USR_STATUS = "status";
    private static final String USR_BIRTH_DATE = "birth_date";

    @Override
    public boolean save(User entity) {
        boolean executed = false;
        String sql = "INSERT INTO " + USR_TABLE + "("+ USR_ID +", "+ USR_NAME +", "+ USR_LAST_NAME +", "+ USR_STATUS +
                ", " + USR_BIRTH_DATE + ")  VALUES(?,?,?,?,?)";
        try{
            PreparedStatement statement = db.createPrepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getLastname());
            statement.setString(4, entity.getStatus());
            statement.setObject(5, entity.getBirthdate());
            executed = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return executed;
    }

    @Override
    boolean update(int id, User entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        boolean executed = false;
        String sql = "DELETE FROM " + USR_TABLE + " WHERE " + USR_ID + " = " + id;
        try{
            Statement statement = db.createStatement();
            executed = statement.executeUpdate(sql) == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return executed;
    }

    @Override
    public User get(int id) {
        User user = null;
        String sql = "SELECT * FROM "+USR_TABLE+" WHERE " + USR_ID + " = '" + id + "'";
        try{
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                user = new User();
                user.setId(id);
                user.setName(resultSet.getString(USR_NAME));
                user.setLastname(resultSet.getString(USR_LAST_NAME));
                user.setStatus(resultSet.getString(USR_STATUS));
                user.setBirthdate((LocalDate) resultSet.getObject(USR_BIRTH_DATE));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return user;
    }

    public List<User> getAll() {
        User user = null;
        List<User> list = null;
        String sql = "SELECT * FROM " + USR_TABLE + ";";
        try{
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            list = new ArrayList<>();
            while(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt(USR_ID));
                user.setName(resultSet.getString(USR_NAME));
                user.setLastname(resultSet.getString(USR_LAST_NAME));
                user.setStatus(resultSet.getString(USR_STATUS));
                user.setBirthdate((LocalDate) resultSet.getObject(USR_BIRTH_DATE));
                list.add(user);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return user;
    }
}
