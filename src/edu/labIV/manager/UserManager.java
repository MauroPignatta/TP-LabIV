package edu.labIV.manager;

import edu.labIV.entity.User;
import edu.labIV.mapper.UserMapper;

public class UserManager {

    private UserMapper userMapper;

    public UserManager() {
        this.userMapper = new UserMapper();
    }

    public void saveUser(User user) {
        userMapper.save(user);
    }

    public void updateStatus(String email, String status) {
        //TODO llamar a update
    }
}
