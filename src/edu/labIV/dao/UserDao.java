package edu.labIV.dao;

import edu.labIV.entity.User;

public class UserDao extends Dao<User> {
    @Override
    boolean save(User entity) {
        return false;
    }

    @Override
    boolean update(int id, User entity) {
        return false;
    }

    @Override
    boolean delete(int id) {
        return false;
    }

    @Override
    User get(int id) {
        return null;
    }
}
