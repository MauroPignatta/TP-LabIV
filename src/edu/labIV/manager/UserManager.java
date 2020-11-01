package edu.labIV.manager;

import edu.labIV.entity.User;
import edu.labIV.exception.UserException;
import edu.labIV.logger.Logger;
import edu.labIV.mapper.UserMapper;
import edu.labIV.validator.UserValidator;

import java.util.List;

public class UserManager {

    private final UserValidator userValidator;
    private final UserMapper userMapper;
    private final Logger logger;

    public UserManager(UserValidator userValidator, UserMapper userMapper, Logger logger) {
        this.userValidator = userValidator;
        this.userMapper = userMapper;
        this.logger = logger;
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
