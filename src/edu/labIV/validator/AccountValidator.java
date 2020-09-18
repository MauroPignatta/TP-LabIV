package edu.labIV.validator;

import edu.labIV.entity.Account;

import java.util.regex.Pattern;

public class AccountValidator {

    public boolean validateAccount(Account account){

        boolean valid = validUserName(account.getUsername());
        valid &= validPass(account.getPassword());
        valid &= validEmail(account.getEmail());

        return valid;
    }

    private boolean validUserName(String username){
        return username != null && Pattern.matches("^([a-zA-Z0-9]+){4,}$", username);
    }

    private boolean validPass(String password){
        return password != null && Pattern.matches("^[a-zA-Z@#$%^&+=](?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}[a-zA-Z0-9]$", password);
    }

    private boolean validEmail(String email){
        return email != null && Pattern.matches("^([a-zA-Z0-9-.]+)@([a-zA-Z0-9-.]+).([a-zA-Z]{2,5})$",email);
    }
}
