package edu.labIV;

import edu.labIV.dao.AccountDao;
import edu.labIV.db.DataBaseConnection;
import edu.labIV.entity.Account;
import edu.labIV.manager.AccountManager;


public class Main {

    public static void main(String[] args) {
        DataBaseConnection dataBaseConnection = DataBaseConnection.getInstance();
        AccountManager accountManager = new AccountManager();

        System.out.println(accountManager.activateAccount("yago@messina.com"));

    }

}
