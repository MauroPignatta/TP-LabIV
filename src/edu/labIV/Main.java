package edu.labIV;

import edu.labIV.db.DataBaseConnection;
import edu.labIV.entity.Account;
import edu.labIV.manager.AccountManager;

public class Main {

    public static void main(String[] args) {
        DataBaseConnection dataBaseConnection = DataBaseConnection.getInstance();

        Account account = new Account("aA12345678", "pepe@gmail.com", false);
        AccountManager accountManager = new AccountManager();
        accountManager.signIn(account);

        //account = dataBaseConnection.selectAccountQuery();
        //System.out.println(account.getEmail() + " " + account.getPassword() + " " + account.isActive());

    }

}
