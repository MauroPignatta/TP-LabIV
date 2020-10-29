package edu.labIV.dao;

import edu.labIV.entity.Friend;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FriendDao extends Dao<Friend> {

    private static final String FRI_TABLE = "friend";
    private static final String FRI_USER_ID = "account_id";
    private static final String FRI_FRIEND_ID = "friend_id";
    private static final String FRI_STATUS = "status";

    @Override
    boolean save(Friend entity) {
        boolean executed = false;
        String sql = "INSERT INTO " + FRI_TABLE + "("+ FRI_USER_ID +", "+ FRI_FRIEND_ID + ")  VALUES(?,?,?)";
        try{
            PreparedStatement statement = db.createPrepareStatement(sql);
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getFriendId());
            statement.setString(3, entity.getStatus());
            executed = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return executed;
    }

    @Override
    boolean update(int id, Friend entity) {
        boolean executed = false;
        String sql = "UPDATE " + FRI_TABLE + " SET " + FRI_STATUS + " = ?" +
                " WHERE " + FRI_USER_ID + " = ?" + "AND" + FRI_FRIEND_ID + " = ?";
        try{
            PreparedStatement statement = db.createPrepareStatement(sql);
            statement.setString(1, entity.getStatus());
            statement.setInt(2, entity.getUserId());
            statement.setInt(3, entity.getFriendId());
            executed = statement.executeUpdate() == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return executed;
    }

    @Override
    boolean delete(int id) {
        boolean executed = false;
        String sql = "DELETE FROM " + FRI_TABLE + " WHERE " + FRI_USER_ID + " = " + entity.getUserId() +
                "AND" + FRI_FRIEND_ID + " = " + entity.getFriendId();
        try{
            Statement statement = db.createStatement();
            executed = statement.executeUpdate(sql) == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return executed;
    }

    @Override
    Friend get(int id) {
        return null;
    }
}
