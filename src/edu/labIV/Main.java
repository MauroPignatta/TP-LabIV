package edu.labIV;


import edu.labIV.entity.User;
import edu.labIV.manager.ManagerGod;

import java.time.LocalDate;


public class Main {

    public static void main(String[] args) {

        ManagerGod managerGod = new ManagerGod();
        User user = new User("Yago", "Messina", LocalDate.of(2001,5,10));
        managerGod.signIn("yago@messina.com", "Aa12345678", user);

    }

}
