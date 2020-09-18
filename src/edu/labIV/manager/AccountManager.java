package edu.labIV.manager;

import edu.labIV.db.DataBaseConnection;
import edu.labIV.entity.Account;
import edu.labIV.validator.AccountValidator;

public class AccountManager {

    private AccountValidator accountValidator;
    private DataBaseConnection dataBaseConnection;

    public AccountManager() {
        this.accountValidator = new AccountValidator();
        this.dataBaseConnection = DataBaseConnection.getInstance();
    }

    public boolean login(Account account){
        boolean logged = false;
        logged = accountValidator.validateAccount(account);
        if(logged){
            //TODO accede a la bd para cambiar el estado
        }
        return logged;
    }

    public boolean logout(Account account){

        //TODO accede a la bd para cambiar el estado
        return true;
    }

    public void signIn(Account account){
        boolean valid = accountValidator.validateAccount(account);
        if(valid){
            if(!dataBaseConnection.insertAccountQuery(account)){
                //TODO mandar mensaje usuario ya existente
            }else{
                //TODO mandar mail de activacion de cuenta
            }
         }else{
            //TODO datos incorrectos
        }
    }
}
