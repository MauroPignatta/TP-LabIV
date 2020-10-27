package edu.labIV;


import edu.labIV.entity.Account;
import edu.labIV.entity.User;
import edu.labIV.manager.ManagerGod;

import java.time.LocalDate;


public class Main {

    public static void main(String[] args) {

        ManagerGod managerGod = new ManagerGod();
        Account account = managerGod.getAccountManager().getAccount("yago@messina.com");
        User user = managerGod.getUserManager().getUser(account.getId());

        user.setName("Vago");
        managerGod.getUserManager().updateUser(user);
    }

}
