package edu.labIV.dao;

import edu.labIV.entity.Friend;
import edu.labIV.entity.Post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDao extends Dao<Post> {

    private static final String POST_TABLE = "post";
    private static final String POST_ID = "post_id";
    private static final String POST_USER_ID = "account_id";
    private static final String POST_TEXT = "text";
    private static final String POST_URL = "url";
    private static final String POST_DATE = "date";

    @Override
    public boolean save(Post entity) {
        boolean executed = false;
        String sql = "INSERT INTO " + POST_TABLE + "(" + POST_USER_ID +", "+ POST_TEXT +", "+ POST_URL +", "+
                POST_DATE +")  VALUES(?,?,?,?)";
        try{
            PreparedStatement statement = db.createPrepareStatement(sql);
            statement.setInt(1, entity.getUserId());
            statement.setString(2, entity.getText());
            statement.setString(3, entity.getUrl());
            statement.setObject(4, entity.getDate());
            executed = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return executed;
    }

    @Override
    protected boolean update(Post entity) {
        return false;
    }

    @Override
    protected boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(int userId, int postId) {
        boolean executed = false;
        String sql = "DELETE FROM " + POST_TABLE + " WHERE " + POST_USER_ID + " = " + userId +
                " AND " + POST_ID + " = " + postId;
        try{
            Statement statement = db.createStatement();
            executed = statement.executeUpdate(sql) == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return executed;
    }

    @Override
    protected Post get(int id) {
        return null;
    }

    @Override
    public Post get(int userId, int postId) {
        Post post = null;
        String sql = "SELECT * FROM "+ POST_TABLE +" WHERE " + POST_USER_ID + " = '" + userId+ "'" +
                " AND " + POST_ID + " = '" + postId + "'";
        try{
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                post = new Post();
                post.setUserId(userId);
                post.setPostId(postId);
                post.setDate(resultSet.getObject(POST_DATE, LocalDateTime.class));
                post.setText(resultSet.getString(POST_TEXT));
                post.setUrl(resultSet.getString(POST_URL));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return post;
    }

    @Override
    protected List<Post> getAll() {
        return null;
    }

    @Override
    public List<Post> getAll(int id) {
        Post post;
        List<Post> postList =  new ArrayList<>();
        String sql = "SELECT * FROM " + POST_DATE + " WHERE " + POST_USER_ID + " = " + id + ";";
        try {
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                post = new Post();
                post.setUserId(resultSet.getInt(POST_USER_ID));
                post.setPostId(resultSet.getInt(POST_ID));
                post.setDate(resultSet.getObject(POST_DATE, LocalDateTime.class));
                post.setText(resultSet.getString(POST_TEXT));
                post.setUrl(resultSet.getString(POST_URL));
                postList.add(post);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return postList;
    }
}
