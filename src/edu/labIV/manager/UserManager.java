package edu.labIV.manager;

import edu.labIV.entity.Account;
import edu.labIV.entity.User;
import edu.labIV.exception.UserException;
import edu.labIV.logger.Logger;
import edu.labIV.mapper.UserMapper;
import edu.labIV.validator.UserValidator;

import java.util.List;

public class UserManager {

    private UserMapper userMapper;
    private Logger logger;
    private UserValidator userValidator;

    public UserManager() {
        this.userMapper = new UserMapper();
        logger = Logger.getInstance();
        userValidator = new UserValidator();
    }

    public boolean saveUser(User user) {
        boolean isSaved = false;
        try {
            userValidator.validateUser(user);
            isSaved = userMapper.save(user);
        } catch (UserException ex) {
            logger.logError(ex.getError());
        }
        return isSaved;
    }

    public User getUser(int id){
        return userMapper.get(id);
    }

    public void deleteUser(int id){
        userMapper.delete(id);
    }

    public void updateUser(User user) {
        userMapper.update(user);
    }

    public List<User> getUserList(){
        return userMapper.getAll();
    }
}
