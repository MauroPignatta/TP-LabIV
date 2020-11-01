package edu.labIV;


import edu.labIV.entity.User;
import edu.labIV.manager.ManagerGod;

public class Main {

    public static void main(String[] args) {

        ManagerGod managerGod = new ManagerGod();

        for(User user : managerGod.getAddableUserList(27)){
            System.out.printf("%s %s\n", user.getName(), user.getLastname());
        }

    }
}


