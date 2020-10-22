package edu.labIV.mapper;

import edu.labIV.dao.UserDao;
import edu.labIV.entity.User;


public class UserMapper {
    
    private UserDao userDao;

    public UserMapper() {
        this.userDao = new UserDao();
    }

    
    public boolean save(User user){
        return userDao.save(user);
    }

    
    public boolean delete(int id){
        return userDao.delete(id);
    }

   
    public User get(int id){
        return userDao.get(id);
    }

  
    public boolean update(User user) {
        boolean updated = false;
        return updated;
    }
}
