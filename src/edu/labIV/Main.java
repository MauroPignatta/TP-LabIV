package edu.labIV;

import edu.labIV.dao.AccountDao;
import edu.labIV.db.DataBaseConnection;
import edu.labIV.entity.Account;
import edu.labIV.exceptions.AccountException;
import edu.labIV.exceptions.InvalidAccountException;
import edu.labIV.manager.AccountManager;
import edu.labIV.validator.AccountValidator;


public class Main {

    public static void main(String[] args) {
        DataBaseConnection dataBaseConnection = DataBaseConnection.getInstance();
        AccountManager accountManager = new AccountManager();

        System.out.println(accountManager.signIn("mp@gmail.com", "Aa123456"));
    }

}
