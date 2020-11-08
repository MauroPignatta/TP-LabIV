package edu.labIV;

import edu.labIV.cfg.Config;
import edu.labIV.cfg.ConfigKey;
import edu.labIV.cfg.ConfigSection;
import edu.labIV.entity.Account;
import edu.labIV.factory.entity.AccountFactory;
import edu.labIV.manager.ManagerGod;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        ManagerGod managerGod = new ManagerGod();
        Account account = new AccountFactory().createNewAccount("pepe@mail.com", "asdasdasd");
        System.out.println(Config.getInstance().getLoginAttempts());
    }
}


