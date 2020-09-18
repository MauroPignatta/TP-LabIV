package edu.labIV;

import edu.labIV.db.DataBaseConnection;
import edu.labIV.entity.Account;
import edu.labIV.manager.AccountManager;

public class Main {

    public static void main(String[] args) {
        DataBaseConnection dataBaseConnection = DataBaseConnection.getInstance();

        //Account account = new Account("Yago", "1234", "yago@messina.com", false);
        //AccountManager accountManager = new AccountManager();
        //accountManager.signIn(account);

        Account account = dataBaseConnection.selectAccountQuery("Yago");
        System.out.println(account.getUsername() + " " + account.getEmail() + " " + account.getPassword() + " " + account.isActive());

    }

}
