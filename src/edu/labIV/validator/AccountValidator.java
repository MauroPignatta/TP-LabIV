package edu.labIV.validator;

import edu.labIV.entity.Account;

public class AccountValidator {

    public boolean validateAccount(Account account){

        boolean valid = validUserName(account.getUsername());
        valid &= validPass(account.getPassword());
        valid &= validEmail(account.getEmail());

        return valid;
    }

    private boolean validUserName(String name){ return !name.isEmpty();}
    private boolean validPass(String name){ return !name.isEmpty();}
    private boolean validEmail(String name){ return !name.isEmpty();}
}
