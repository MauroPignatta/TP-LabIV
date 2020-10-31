package edu.labIV;


import edu.labIV.entity.Post;
import edu.labIV.entity.User;
import edu.labIV.manager.ManagerGod;

import java.time.LocalDateTime;

public class Main {


    public static void main(String[] args) {

        ManagerGod managerGod = new ManagerGod();

        for(User user : managerGod.getAddableUser(30)){
            System.out.printf("%s %s\n", user.getName(), user.getLastname());
        }

    }
}


